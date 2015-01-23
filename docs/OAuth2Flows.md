OAuth2 Flows
============

4.1 - Authorization Code Grant
------------------------------
1. Third Party directs the browser to GET /api/oauth2/authorize
    - response_type = "code"
    - client_id = Client Identifier
    - redirect_uri = Redirect URI to use
    - scope = Scopes to request
    - state = Generated Nonce to confirm response is from this request. This should be a single-use token.
2. End User authenticated in the browser
3. Browser is sent back to GET redirect_uri, with parameters on the Query String
    - state = The same State value as above
    - code = The authorization code that they will need next. This should be a single-use token.
4. Third Party requests an access token by doing a POST /api/oauth2/token
    - grant_type = authorization_code
    - code = The code that was returned in the above redirect
    - redirect_uri = The same Redirect URI that was used in the above request
    - client_id = The Client Identified
5. Response to Third Party is a JSON Payload containing the Access Token
    - access_token = The Access Token itself
    - token_type = The Token Type. "Bearer"
    - expires_in = The Expiry of the Access Token, in seconds
    - scope = The scopes the access token is valid for. 
    - refresh_token = The Refresh Token to get a new Access Token with

4.2 - Implicit Grant
--------------------
1. Third Party directs the browser to GET /api/oauth2/authorize
    - response_type = "token"
    - client_id = Client Identifier
    - redirect_uri = Redirect URI to use
    - scope = Scopes to request
    - state = Generated Nonce to confirm response is from this request. This should be a single-use token.
2. End User authenticated in the browser
3. Browser is sent back to GET redirect_uri, with parameters formatted as a Query String but included in the Fragment
    - access_token = The Access Token itself
    - token_type = The Token Type. "Bearer"
    - expires_in = The Expiry of the Access Token, in seconds
    - scope = The scopes the access token is valid for. 
    - state = The same State value as above
    
4.3 - Resource Owner Password Credentials Grant
-----------------------------------------------
1. Third Party obtains Username and Password from user
2. Third Party requests an access token by doing a POST /api/oauth2/token
    - grant_type = "password"
    - username = The Username to use
    - password = The Password to use
    - scope = Scopes to request
3. Response to Third Party is a JSON Payload containing the Access Token
    - access_token = The Access Token itself
    - token_type = The Token Type. "Bearer"
    - expires_in = The Expiry of the Access Token, in seconds
    - scope = The scopes the access token is valid for. 
    - refresh_token = The Refresh Token to get a new Access Token with
    
4.4 - Client Credentials Grant
------------------------------
1. Third Party requests an access token by doing a POST /api/oauth2/token
    - grant_type = "client_credentials"
    - scope = Scopes to request
2. Response to Third Party is a JSON Payload containing the Access Token
    - access_token = The Access Token itself
    - token_type = The Token Type. "Bearer"
    - expires_in = The Expiry of the Access Token, in seconds
    - scope = The scopes the access token is valid for. 