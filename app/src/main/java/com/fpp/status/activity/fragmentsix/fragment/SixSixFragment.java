package com.fpp.status.activity.fragmentsix.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fpp.status.R;
import com.fpp.status.activity.fragmentsix.AddServiceCategoryAdapter;
import com.fpp.status.entity.LVOne;
import com.fpp.status.entity.LVTwo;
import com.fpp.status.entity.ServiceCategory;
import com.fpp.status.entity.ServiceItem;
import com.fpp.status.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by fupengpeng on 2017/12/15 0015.
 */

public class SixSixFragment extends BaseFragment {

    @BindView(R.id.lv_fragment_add_service_item)
    ListView lvFragmentAddServiceItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_six_six);

        initView();
        return view;
    }

    private void initView() {
        List<ServiceCategory> lvOnes = getData();

        AddServiceCategoryAdapter lvAdapter = new AddServiceCategoryAdapter(getActivity(), lvOnes);
        lvFragmentAddServiceItem.setAdapter(lvAdapter);


    }

    /**
     * 设置数据
     *
     * @return
     */
    public List<ServiceCategory> getData() {
        List<ServiceCategory> serviceCategorys = new ArrayList<ServiceCategory>();

        //护理类
        ServiceCategory serviceCategoryOne = new ServiceCategory();
        serviceCategoryOne.setServiceCategoryID("1");
        serviceCategoryOne.setServiceCategoryName("护理类");
        List<ServiceItem> serviceItemsOne = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("倒膜" + j);
            serviceItem.setDiscount("0");
            serviceItemsOne.add(serviceItem);
        }
        serviceCategoryOne.setServiceItemList(serviceItemsOne);
        serviceCategorys.add(serviceCategoryOne);

        //美容类
        ServiceCategory serviceCategoryTwo = new ServiceCategory();
        serviceCategoryTwo.setServiceCategoryID("2");
        serviceCategoryTwo.setServiceCategoryName("美容类");
        List<ServiceItem> serviceItemsTwo = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("修眉" + j);
            serviceItem.setDiscount("0");
            serviceItemsTwo.add(serviceItem);
        }
        serviceCategoryTwo.setServiceItemList(serviceItemsTwo);
        serviceCategorys.add(serviceCategoryTwo);

        //基础项目
        ServiceCategory serviceCategoryThree = new ServiceCategory();
        serviceCategoryThree.setServiceCategoryID("3");
        serviceCategoryThree.setServiceCategoryName("基础项目");
        List<ServiceItem> serviceItemsThree = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("洗吹" + j);
            serviceItem.setDiscount("0");
            serviceItemsThree.add(serviceItem);
        }
        serviceCategoryThree.setServiceItemList(serviceItemsThree);
        serviceCategorys.add(serviceCategoryThree);

        //烫发类
        ServiceCategory serviceCategoryFour = new ServiceCategory();
        serviceCategoryFour.setServiceCategoryID("4");
        serviceCategoryFour.setServiceCategoryName("烫发类");
        List<ServiceItem> serviceItemsFour = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("烫发" + j);
            serviceItem.setDiscount("0");
            serviceItemsFour.add(serviceItem);
        }
        serviceCategoryFour.setServiceItemList(serviceItemsFour);
        serviceCategorys.add(serviceCategoryFour);

        //染发类
        ServiceCategory serviceCategoryFive = new ServiceCategory();
        serviceCategoryFive.setServiceCategoryID("5");
        serviceCategoryFive.setServiceCategoryName("染发类");
        List<ServiceItem> serviceItemsFive = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("染发" + j);
            serviceItem.setDiscount("0");
            serviceItemsFive.add(serviceItem);
        }
        serviceCategoryFive.setServiceItemList(serviceItemsFive);
        serviceCategorys.add(serviceCategoryFive);

        //染发类
        ServiceCategory serviceCategoryF = new ServiceCategory();
        serviceCategoryF.setServiceCategoryID("5");
        serviceCategoryF.setServiceCategoryName("染发类f");
        List<ServiceItem> serviceItemsF = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("染发f" + j);
            serviceItem.setDiscount("0");
            serviceItemsF.add(serviceItem);
        }
        serviceCategoryF.setServiceItemList(serviceItemsF);
        serviceCategorys.add(serviceCategoryF);

        //染发类
        ServiceCategory serviceCategorySix = new ServiceCategory();
        serviceCategorySix.setServiceCategoryID("5");
        serviceCategorySix.setServiceCategoryName("染发类6");
        List<ServiceItem> serviceItemsSix = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("染发6" + j);
            serviceItem.setDiscount("0");
            serviceItemsSix.add(serviceItem);
        }
        serviceCategorySix.setServiceItemList(serviceItemsSix);
        serviceCategorys.add(serviceCategorySix);

        //染发类
        ServiceCategory serviceCategorySeven = new ServiceCategory();
        serviceCategorySeven.setServiceCategoryID("5");
        serviceCategorySeven.setServiceCategoryName("染发类7");
        List<ServiceItem> serviceItemsSeven = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("染发7" + j);
            serviceItem.setDiscount("0");
            serviceItemsSeven.add(serviceItem);
        }
        serviceCategorySeven.setServiceItemList(serviceItemsSeven);
        serviceCategorys.add(serviceCategorySeven);

        //染发类
        ServiceCategory serviceCategoryEight = new ServiceCategory();
        serviceCategoryEight.setServiceCategoryID("5");
        serviceCategoryEight.setServiceCategoryName("染发类8");
        List<ServiceItem> serviceItemsEight = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("染发8" + j);
            serviceItem.setDiscount("0");
            serviceItemsEight.add(serviceItem);
        }
        serviceCategoryEight.setServiceItemList(serviceItemsEight);
        serviceCategorys.add(serviceCategoryEight);

        //染发类
        ServiceCategory serviceCategoryNine = new ServiceCategory();
        serviceCategoryNine.setServiceCategoryID("5");
        serviceCategoryNine.setServiceCategoryName("染发类9");
        List<ServiceItem> serviceItemsNine = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("染发9" + j);
            serviceItem.setDiscount("0");
            serviceItemsNine.add(serviceItem);
        }
        serviceCategoryNine.setServiceItemList(serviceItemsNine);
        serviceCategorys.add(serviceCategoryNine);

        //染发类
        ServiceCategory serviceCategoryEleven = new ServiceCategory();
        serviceCategoryEleven.setServiceCategoryID("5");
        serviceCategoryEleven.setServiceCategoryName("染发类11");
        List<ServiceItem> serviceItemsEleven = new ArrayList<ServiceItem>();
        for (int j = 0; j < 10; j++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setSalePrice("12.00");
            serviceItem.setServiceItemName("染发11" + j);
            serviceItem.setDiscount("0");
            serviceItemsEleven.add(serviceItem);
        }
        serviceCategoryEleven.setServiceItemList(serviceItemsEleven);
        serviceCategorys.add(serviceCategoryEleven);


        return serviceCategorys;
    }


}
