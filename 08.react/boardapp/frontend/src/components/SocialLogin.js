import React from "react";
import { GoogleLogin } from "react-google-login";
import { FacebookLoginButton } from "react-social-login-buttons";

const SocialLogin = () => {
  const responseGoogle = (response) => {
    console.log(response);
    // Send Google token to backend for verification
  };

  const responseFacebook = (response) => {
    console.log(response);
    // Send Facebook token to backend for verification
  };

  return (
    <div>
      <GoogleLogin
        clientId='YOUR_GOOGLE_CLIENT_ID'
        buttonText='Login with Google'
        onSuccess={responseGoogle}
        onFailure={responseGoogle}
      />
      <FacebookLoginButton
        appId='YOUR_FACEBOOK_CLIENT_ID'
        autoLoad={false}
        fields='name,email,picture'
        callback={responseFacebook}
      />
    </div>
  );
};

export default SocialLogin;
