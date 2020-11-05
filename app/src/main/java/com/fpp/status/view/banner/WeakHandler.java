package com.fpp.status.view.banner;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import com.fpp.status.utils.LogUtil;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
@SuppressWarnings("unused")
public class WeakHandler {
    private final Handler.Callback mCallback; // hard reference to Callback. We need to keep callback in memory
    private final ExecHandler mExec;
    private Lock mLock = new ReentrantLock();
    @SuppressWarnings("ConstantConditions")
    @VisibleForTesting
    final ChainedRef mRunnables = new ChainedRef(mLock, null);

    /**
     * Default constructor associates this handler with the {@link Looper} for the
     * current thread.
     *
     * If this thread does not have a looper, this handler won't be able to receive messages
     * so an exception is thrown.
     */
    public WeakHandler() {
        mCallback = null;
        mExec = new ExecHandler();
        LogUtil.e("WeakHandler  " + "WeakHandler构造器 -- 001");
    }

    /**
     * Constructor associates this handler with the {@link Looper} for the
     * current thread and takes a callback interface in which you can handle
     * messages.
     *
     * If this thread does not have a looper, this handler won't be able to receive messages
     * so an exception is thrown.
     *
     * @param callback The callback interface in which to handle messages, or null.
     */
    public WeakHandler(@Nullable Handler.Callback callback) {
        mCallback = callback; // Hard referencing body
        mExec = new ExecHandler(new WeakReference<>(callback)); // Weak referencing inside ExecHandler
        LogUtil.e("WeakHandler  " + "WeakHandler构造器 -- 002");
    }

    /**
     * Use the provided {@link Looper} instead of the default one.
     *
     * @param looper The looper, must not be null.
     */
    public WeakHandler(@NonNull Looper looper) {
        mCallback = null;
        mExec = new ExecHandler(looper);
        LogUtil.e("WeakHandler  " + "WeakHandler构造器 -- 003-----looper"+looper);
    }

    /**
     * Use the provided {@link Looper} instead of the default one and take a callback
     * interface in which to handle messages.
     *
     * @param looper The looper, must not be null.
     * @param callback The callback interface in which to handle messages, or null.
     */
    public WeakHandler(@NonNull Looper looper, @NonNull Handler.Callback callback) {
        mCallback = callback;
        mExec = new ExecHandler(looper, new WeakReference<>(callback));
        LogUtil.e("WeakHandler  " + "WeakHandler构造器 -- 004" + looper);
    }

    /**
     * Causes the Runnable r to be added to the message queue.
     * The runnable will be run on the thread to which this handler is
     * attached.
     *  使可运行的r被添加到消息队列中。 runnable将会在这个处理器附加的线程上运行。
     * @param r The Runnable that will be executed.  将被执行的Runnable。
     *
     * @return Returns true if the Runnable was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.
     *         如果Runnable被成功地放置到消息队列中，则返回true。
     *         在失败时返回false，通常是因为looper处理消息队列正在退出。
     */
    public final boolean post(@NonNull Runnable r) {
        LogUtil.e("post  " + "WeakHandler构造器 -- 004"  + r.toString());
        return mExec.post(wrapRunnable(r));
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run
     * at a specific time given by <var>uptimeMillis</var>.
     * <b>The time-base is {@link android.os.SystemClock#uptimeMillis}.</b>
     * The runnable will be run on the thread to which this handler is attached.
     *
     * @param r The Runnable that will be executed.
     * @param uptimeMillis The absolute time at which the callback should run,
     *         using the {@link android.os.SystemClock#uptimeMillis} time-base.
     *
     * @return Returns true if the Runnable was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.  Note that a
     *         result of true does not mean the Runnable will be processed -- if
     *         the looper is quit before the delivery time of the message
     *         occurs then the message will be dropped.
     */
    public final boolean postAtTime(@NonNull Runnable r, long uptimeMillis) {
        LogUtil.e("postAtTime  " + "添加指定任务到消息队列中，以便在指定的时间运行"
                + "   r = "  +  r.toString()
                + "   uptimeMillis = "  +  uptimeMillis
        );
        return mExec.postAtTime(wrapRunnable(r), uptimeMillis);
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run
     * at a specific time given by <var>uptimeMillis</var>.
     * <b>The time-base is {@link android.os.SystemClock#uptimeMillis}.</b>
     * The runnable will be run on the thread to which this handler is attached.
     *
     * @param r The Runnable that will be executed.
     * @param uptimeMillis The absolute time at which the callback should run,
     *         using the {@link android.os.SystemClock#uptimeMillis} time-base.
     *
     * @return Returns true if the Runnable was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.  Note that a
     *         result of true does not mean the Runnable will be processed -- if
     *         the looper is quit before the delivery time of the message
     *         occurs then the message will be dropped.
     *
     * @see android.os.SystemClock#uptimeMillis
     */
    public final boolean postAtTime(Runnable r, Object token, long uptimeMillis) {
        LogUtil.e("postAtTime  " + "添加指定任务到消息队列中，以便在指定的时间运行"
                + "   r = "  +  r.toString()
                + "   uptimeMillis = "  +  uptimeMillis
                + "   token = "  +  token
        );
        return mExec.postAtTime(wrapRunnable(r), token, uptimeMillis);
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run
     * after the specified amount of time elapses.
     * The runnable will be run on the thread to which this handler
     * is attached.
     * 使可运行的r被添加到消息队列中，以便在指定的时间流逝后运行。
     * runnable将会在这个处理器附加的线程上运行。
     *
     * @param r The Runnable that will be executed.
     * @param delayMillis The delay (in milliseconds) until the Runnable
     *        will be executed.
     *
     * @return Returns true if the Runnable was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.  Note that a
     *         result of true does not mean the Runnable will be processed --
     *         if the looper is quit before the delivery time of the message
     *         occurs then the message will be dropped.
     */
    public final boolean postDelayed(Runnable r, long delayMillis) {
        LogUtil.e("postDelayed  " + "使可运行的r被添加到消息队列中，以便在指定的时间流逝后运行。\n" +
                "runnable将会在这个处理器附加的线程上运行。"
                + "   r = "  +  r.hashCode()
                + "   delayMillis = "  +  delayMillis
        );
        return mExec.postDelayed(wrapRunnable(r), delayMillis);
    }

    /**
     * Posts a message to an object that implements Runnable.
     * Causes the Runnable r to executed on the next iteration through the
     * message queue. The runnable will be run on the thread to which this
     * handler is attached.
     * 将一条消息发布到一个实现Runnable的对象上。
     * 使Runnable r在下一次迭代中通过消息队列执行。runnable将会在这个处理器附加的线程上运行。
     * <b>This method is only for use in very special circumstances -- it
     * can easily starve the message queue, cause ordering problems, or have
     * other unexpected side-effects.</b>
     *
     * @param r The Runnable that will be executed.
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.
     */
    public final boolean postAtFrontOfQueue(Runnable r) {
        LogUtil.e("removeCallbacks  " + "      将一条消息发布到一个实现Runnable的对象上。\n" +
                "        使Runnable r在下一次迭代中通过消息队列执行。runnable将会在这个处理器附加的线程上运行。"
                + "   r = "  +  r.toString()
        );

        return mExec.postAtFrontOfQueue(wrapRunnable(r));
    }

    /**
     * Remove any pending posts of Runnable r that are in the message queue.
     * 删除在消息队列中Runnable r的任何待定帖子。
     */
    public final void removeCallbacks(Runnable r) {
        final WeakRunnable runnable = mRunnables.remove(r);
        LogUtil.e("removeCallbacks  " + "删除在消息队列中Runnable r 的任务  "
                + "   r = "  +  r.hashCode()
        );
        if (runnable != null) {
            LogUtil.e("removeCallbacks  " + "删除在消息队列中Runnable r的任何待定帖子。"
                    + "   r = "  +  r.toString()
            );
            mExec.removeCallbacks(runnable);
        }
    }

    /**
     * Remove any pending posts of Runnable <var>r</var> with Object
     * <var>token</var> that are in the message queue.  If <var>token</var> is null,
     * all callbacks will be removed.
     * 在消息队列中删除Runnable的任何待定帖子。如果“令牌”为空，则所有回调都将被删除。
     */
    public final void removeCallbacks(Runnable r, Object token) {
        final WeakRunnable runnable = mRunnables.remove(r);
        LogUtil.e("removeCallbacks  " + "删除在消息队列中Runnable r的任何待定帖子。"
                + "   r = "  +  r.toString()
                + "   token = "  +  token
        );
        if (runnable != null) {
            LogUtil.e("removeCallbacks  " + "删除在消息队列中Runnable r的任何待定帖子。"
                    + "   r = "  +  r.toString()
                    + "   token = "  +  token
            );
            mExec.removeCallbacks(runnable, token);
        }
    }

    /**
     * Pushes a message onto the end of the message queue after all pending messages
     * before the current time. It will be received in callback,
     * in the thread attached to this handler.
     * 在当前时间之前，将一条消息推到消息队列的末尾。它将在回调中接收，在连接到这个处理器的线程中。
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.
     */
    public final boolean sendMessage(Message msg) {
        LogUtil.e("sendMessage  " + "在当前时间之前，将一条消息推到消息队列的末尾。它将在回调中接收，在连接到这个处理器的线程中。。"
                + "   msg = "  +  msg.toString()
        );
        return mExec.sendMessage(msg);
    }

    /**
     * Sends a Message containing only the what value.
     * 只发送包含该值的消息。
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.
     */
    public final boolean sendEmptyMessage(int what) {
        LogUtil.e("sendEmptyMessage  " + "只发送包含该值的消息。"
                + "   what = "  +  what
        );
        return mExec.sendEmptyMessage(what);
    }

    /**
     * Sends a Message containing only the what value, to be delivered
     * after the specified amount of time elapses.
     * @see #sendMessageDelayed(Message, long)
     *
     * 发送一个只包含该值的消息，在指定的时间之后发送。
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.
     */
    public final boolean sendEmptyMessageDelayed(int what, long delayMillis) {
        LogUtil.e("sendEmptyMessageDelayed  " + "发送一个只包含该值的消息，在指定的时间之后发送。"
                + "   what = "  +  what
                + "   delayMillis = "  +  delayMillis
        );
        return mExec.sendEmptyMessageDelayed(what, delayMillis);
    }

    /**
     * Sends a Message containing only the what value, to be delivered
     * at a specific time.
     * @see #sendMessageAtTime(Message, long)
     * 发送一个仅包含该值的消息，在特定的时间发送。
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.
     */
    public final boolean sendEmptyMessageAtTime(int what, long uptimeMillis) {
        LogUtil.e("sendEmptyMessageAtTime  " + "发送一个只包含该值的消息，在特定的时间发送。"
                + "   what = "  +  what
                + "   uptimeMillis = "  +  uptimeMillis
        );
        return mExec.sendEmptyMessageAtTime(what, uptimeMillis);
    }

    /**
     * Enqueue a message into the message queue after all pending messages
     * before (current time + delayMillis). You will receive it in
     * callback, in the thread attached to this handler.
     *
     * 在所有未决消息（当前时间+delayMillis）之后，将一条消息放入消息队列中。您将在回调中接收它，在这个处理程序附带的线程中。
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.  Note that a
     *         result of true does not mean the message will be processed -- if
     *         the looper is quit before the delivery time of the message
     *         occurs then the message will be dropped.
     */
    public final boolean sendMessageDelayed(Message msg, long delayMillis) {
        LogUtil.e("sendMessageDelayed  " + "在所有未决消息（当前时间+delayMillis）之后，将一条消息放入消息队列中。您将在回调中接收它，在这个处理程序附带的线程中。"
                + "   msg = "  +  msg
                + "   delayMillis = "  +  delayMillis
        );
        return mExec.sendMessageDelayed(msg, delayMillis);
    }

    /**
     * Enqueue a message into the message queue after all pending messages
     * before the absolute time (in milliseconds) <var>uptimeMillis</var>.
     * <b>The time-base is {@link android.os.SystemClock#uptimeMillis}.</b>
     * You will receive it in callback, in the thread attached
     * to this handler.
     *
     * @param uptimeMillis The absolute time at which the message should be
     *         delivered, using the
     *         {@link android.os.SystemClock#uptimeMillis} time-base.
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.  Note that a
     *         result of true does not mean the message will be processed -- if
     *         the looper is quit before the delivery time of the message
     *         occurs then the message will be dropped.
     */
    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        LogUtil.e("sendMessageAtTime  " + "  "
                + "   msg = "  +  msg
                + "   uptimeMillis = "  +  uptimeMillis
        );

        return mExec.sendMessageAtTime(msg, uptimeMillis);
    }

    /**
     * Enqueue a message at the front of the message queue, to be processed on
     * the next iteration of the message loop.  You will receive it in
     * callback, in the thread attached to this handler.
     * <b>This method is only for use in very special circumstances -- it
     * can easily starve the message queue, cause ordering problems, or have
     * other unexpected side-effects.</b>
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.
     */
    public final boolean sendMessageAtFrontOfQueue(Message msg) {
        LogUtil.e("sendMessageAtFrontOfQueue  " + "  "
                + "   msg = "  +  msg
        );
        return mExec.sendMessageAtFrontOfQueue(msg);
    }

    /**
     * Remove any pending posts of messages with code 'what' that are in the
     * message queue.
     */
    public final void removeMessages(int what) {

        LogUtil.e("removeMessages  " + "  "
                + "   what = "  +  what
        );
        mExec.removeMessages(what);
    }

    /**
     * Remove any pending posts of messages with code 'what' and whose obj is
     * 'object' that are in the message queue.  If <var>object</var> is null,
     * all messages will be removed.
     */
    public final void removeMessages(int what, Object object) {

        LogUtil.e("removeMessages  " + "  "
                + "   what = "  +  what
                + "   object = "  +  object
        );
        mExec.removeMessages(what, object);
    }

    /**
     * Remove any pending posts of callbacks and sent messages whose
     * <var>obj</var> is <var>token</var>.  If <var>token</var> is null,
     * all callbacks and messages will be removed.
     */
    public final void removeCallbacksAndMessages(Object token) {
        LogUtil.e("removeCallbacksAndMessages  " + "  "
                + "   token = "  +  token
                + "   token = "  +  token
        );
        mExec.removeCallbacksAndMessages(token);
    }

    /**
     * Check if there are any pending posts of messages with code 'what' in
     * the message queue.
     */
    public final boolean hasMessages(int what) {
        LogUtil.e("hasMessages  " + "  "
                + "   what = "  +  what
                + "   what = "  +  what
        );

        return mExec.hasMessages(what);
    }

    /**
     * Check if there are any pending posts of messages with code 'what' and
     * whose obj is 'object' in the message queue.
     */
    public final boolean hasMessages(int what, Object object) {
        LogUtil.e("hasMessages  " + "  "
                + "   what = "  +  what
                + "   object = "  +  object
        );
        return mExec.hasMessages(what, object);
    }

    public final Looper getLooper() {
        LogUtil.e("getLooper  " + "  "
        );
        return mExec.getLooper();
    }

    private WeakRunnable wrapRunnable(@NonNull Runnable r) {
        //noinspection ConstantConditions
        if (r == null) {
            throw new NullPointerException("Runnable can't be null");
        }
        final ChainedRef hardRef = new ChainedRef(mLock, r);
        mRunnables.insertAfter(hardRef);
        LogUtil.e("wrapRunnable  " + "   加锁，执行任务？？？ "
                + "   r = "  +  r.hashCode()
        );
        return hardRef.wrapper;
    }

    private static class ExecHandler extends Handler {
        private final WeakReference<Callback> mCallback;

        ExecHandler() {
            mCallback = null;
        }

        ExecHandler(WeakReference<Callback> callback) {
            mCallback = callback;
        }

        ExecHandler(Looper looper) {
            super(looper);
            mCallback = null;
        }

        ExecHandler(Looper looper, WeakReference<Callback> callback) {
            super(looper);
            mCallback = callback;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (mCallback == null) {
                return;
            }

            final Callback callback = mCallback.get();
            if (callback == null) { // Already disposed
                return;
            }
            LogUtil.e("ExecHandler   handleMessage  " + "  "
                    + "   msg = "  +  msg.toString()
            );
            callback.handleMessage(msg);
        }
    }

    static class WeakRunnable implements Runnable {
        private final WeakReference<Runnable> mDelegate;
        private final WeakReference<ChainedRef> mReference;

        WeakRunnable(WeakReference<Runnable> delegate, WeakReference<ChainedRef> reference) {
            mDelegate = delegate;
            mReference = reference;
        }

        @Override
        public void run() {
            LogUtil.e("WeakRunnable   run  " + "  启动线程执行任务 "  + this.hashCode()
            );
            final Runnable delegate = mDelegate.get();
            final ChainedRef reference = mReference.get();
            if (reference != null) {
                reference.remove();
            }
            if (delegate != null) {
                delegate.run();
            }
        }
    }

    static class ChainedRef {
        @Nullable
        ChainedRef next;
        @Nullable
        ChainedRef prev;
        @NonNull
        final Runnable runnable;
        @NonNull
        final WeakRunnable wrapper;

        @NonNull
        Lock lock;

        public ChainedRef(@NonNull Lock lock, @NonNull Runnable r) {
            this.runnable = r;
            this.lock = lock;
            this.wrapper = new WeakRunnable(new WeakReference<>(r), new WeakReference<>(this));
        }

        public WeakRunnable remove() {
            lock.lock();
            LogUtil.e("WeakRunnable   remove  " + "   移除  任务  "
                    + "   lock = "  +  lock.toString()
            );
            try {
                if (prev != null) {
                    prev.next = next;
                }
                if (next != null) {
                    next.prev = prev;
                }
                prev = null;
                next = null;
            } finally {
                lock.unlock();
            }
            return wrapper;
        }

        public void insertAfter(@NonNull ChainedRef candidate) {
            lock.lock();
            LogUtil.e("WeakRunnable   insertAfter  " + "  插入任务后，给线程加锁 "
                    + "   candidate = "  +  candidate.hashCode()
            );
            try {
                if (this.next != null) {
                    this.next.prev = candidate;
                }

                candidate.next = this.next;
                this.next = candidate;
                candidate.prev = this;
            } finally {
                lock.unlock();
            }
        }

        @Nullable
        public WeakRunnable remove(Runnable obj) {
            lock.lock();

            LogUtil.e("WeakRunnable   remove  " + "  删除消息队列中的任务 "
                    + "   obj = "  +  obj.hashCode()
            );
            try {
                ChainedRef curr = this.next; // Skipping head
                while (curr != null) {
                    if (curr.runnable == obj) { // We do comparison exactly how Handler does inside
                        return curr.remove();
                    }
                    curr = curr.next;
                }
            } finally {
                lock.unlock();
            }
            return null;
        }
    }
}