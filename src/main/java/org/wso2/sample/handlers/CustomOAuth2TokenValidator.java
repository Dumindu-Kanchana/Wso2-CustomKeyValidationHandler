package org.wso2.sample.handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth2.IdentityOAuth2Exception;
import org.wso2.carbon.identity.oauth2.dto.OAuth2TokenValidationRequestDTO;
import org.wso2.carbon.identity.oauth2.validators.OAuth2TokenValidationMessageContext;
import org.wso2.carbon.identity.oauth2.validators.OAuth2TokenValidator;

public class CustomOAuth2TokenValidator implements OAuth2TokenValidator {

    public static final String TOKEN_TYPE = "bearer";
    private static final String ACCESS_TOKEN_DO = "AccessTokenDO";
    private static final String RESOURCE = "resource";
    private Log log = LogFactory.getLog(CustomOAuth2TokenValidator.class);

    @Override
    public boolean validateAccessDelegation(OAuth2TokenValidationMessageContext messageContext)
            throws IdentityOAuth2Exception {

        // By default we don't validate access delegation
        return true;
    }

    /**
     * Validate scope of the access token using scope validators registered for that specific app.
     *
     * @param messageContext Message context of the token validation request
     * @return Whether validation success or not
     * @throws IdentityOAuth2Exception Exception during while validation
     */
    @Override
    public boolean validateScope(OAuth2TokenValidationMessageContext messageContext) throws IdentityOAuth2Exception {

        return true;    //scope validation happens in IS side always return true.
    }

    /**
     *  Extract the resource from the access token validation request message
     * @param messageContext Message context of the token validation request
     * @return resource
     */
    private String getResourceFromMessageContext(OAuth2TokenValidationMessageContext messageContext) {
        String resource = null;
        if (messageContext.getRequestDTO().getContext() != null) {
            //Iterate the array of context params to find the 'resource' context param.
            for (OAuth2TokenValidationRequestDTO.TokenValidationContextParam resourceParam :
                    messageContext.getRequestDTO().getContext()) {
                //If the context param is the resource that is being accessed
                if (resourceParam != null && RESOURCE.equals(resourceParam.getKey())) {
                    resource = resourceParam.getValue();
                    break;
                }
            }
        }
        return resource;
    }

    // For validation of token profile specific items.
    // E.g. validation of HMAC signature in HMAC token profile
    @Override
    public boolean validateAccessToken(OAuth2TokenValidationMessageContext validationReqDTO)
            throws IdentityOAuth2Exception {

        // With bearer token we don't validate anything apart from access delegation and scopes
        return true;
    }

}

