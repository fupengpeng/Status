package com.fpp.status.activity.twelve.one;

public class Token {

    /**
     * requestId : BA027F96-9A98-45EB-B779-4A1BC539436F
     * credentials : {"securityToken":"CAISlQJ1q6Ft5B2yfSjIr5fyLoKHubBChaO5cRHy01QQe9tgqvSekDz2IH1MeHlrA+Act/UwnGlW6fcSlrJaT55UWErjVvBM6Zda9yysZYfbstCy94YDjJD9xcJCmKu9jqHoeOzcYI73WJXEMiLp9EJaxb/9ak/RPTiMOoGIjphKd8keWhLCAxNNGNZRIHkJyqZYTwyzU8ygKRn3mGHdIVN1sw5n8wNF5L+439eX52i17jS46JdM+96tfsb9P5A0bMYhDons5oEsKPqdihw3wgNR6aJ7gJZD/Tr6pdyHCzFTmU7ba7aEqYc/dlYoOfBjRfEV/OKPnPl5q/HVkJ/s1xFOMOdaXiLSXom8x9HeH+ekJg/M7T82ot+nGoABJxCg9VTGrkVaDTwhiEUmImLlI6o45O5x57MyZmd6wqLnbqnDeRLthD605jKPfV7RhvgYSy7oLv11uQ5zVqOfiX3hsaUnhgkjIZmpiMEisV0PWAvrr8PIjQs97T0z29OJzZLbO5EJzvf1fS7ABF9t2ZaK7gG05Q7ITt7qgadMRb8=","accessKeySecret":"7dZs1uDvZS96XDww2UEpKXq9WUdieB8PrE39F8KPNL4m","accessKeyId":"STS.NTGe83Toc2aRs7C7TEwTLE15r","expiration":"2020-11-19T07:35:54Z"}
     * assumedRoleUser : {"arn":"acs:ram::1424461810896561:role/aliyunosstokengeneratorrole/AliyunOSSTokenGeneratorRole","assumedRoleId":"350483282197016798:AliyunOSSTokenGeneratorRole"}
     */

    private String requestId;
    private CredentialsBean credentials;
    private AssumedRoleUserBean assumedRoleUser;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public CredentialsBean getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsBean credentials) {
        this.credentials = credentials;
    }

    public AssumedRoleUserBean getAssumedRoleUser() {
        return assumedRoleUser;
    }

    public void setAssumedRoleUser(AssumedRoleUserBean assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
    }

    public static class CredentialsBean {
        /**
         * securityToken : CAISlQJ1q6Ft5B2yfSjIr5fyLoKHubBChaO5cRHy01QQe9tgqvSekDz2IH1MeHlrA+Act/UwnGlW6fcSlrJaT55UWErjVvBM6Zda9yysZYfbstCy94YDjJD9xcJCmKu9jqHoeOzcYI73WJXEMiLp9EJaxb/9ak/RPTiMOoGIjphKd8keWhLCAxNNGNZRIHkJyqZYTwyzU8ygKRn3mGHdIVN1sw5n8wNF5L+439eX52i17jS46JdM+96tfsb9P5A0bMYhDons5oEsKPqdihw3wgNR6aJ7gJZD/Tr6pdyHCzFTmU7ba7aEqYc/dlYoOfBjRfEV/OKPnPl5q/HVkJ/s1xFOMOdaXiLSXom8x9HeH+ekJg/M7T82ot+nGoABJxCg9VTGrkVaDTwhiEUmImLlI6o45O5x57MyZmd6wqLnbqnDeRLthD605jKPfV7RhvgYSy7oLv11uQ5zVqOfiX3hsaUnhgkjIZmpiMEisV0PWAvrr8PIjQs97T0z29OJzZLbO5EJzvf1fS7ABF9t2ZaK7gG05Q7ITt7qgadMRb8=
         * accessKeySecret : 7dZs1uDvZS96XDww2UEpKXq9WUdieB8PrE39F8KPNL4m
         * accessKeyId : STS.NTGe83Toc2aRs7C7TEwTLE15r
         * expiration : 2020-11-19T07:35:54Z
         */

        private String securityToken;
        private String accessKeySecret;
        private String accessKeyId;
        private String expiration;

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }
    }

    public static class AssumedRoleUserBean {
        /**
         * arn : acs:ram::1424461810896561:role/aliyunosstokengeneratorrole/AliyunOSSTokenGeneratorRole
         * assumedRoleId : 350483282197016798:AliyunOSSTokenGeneratorRole
         */

        private String arn;
        private String assumedRoleId;

        public String getArn() {
            return arn;
        }

        public void setArn(String arn) {
            this.arn = arn;
        }

        public String getAssumedRoleId() {
            return assumedRoleId;
        }

        public void setAssumedRoleId(String assumedRoleId) {
            this.assumedRoleId = assumedRoleId;
        }
    }
}
