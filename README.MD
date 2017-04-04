Using the API :
===============

The first step is to get an instance of `MastodonApp` (your app). Then, the second step is to authenticate the user. Finally, you get a `MastodonSession` with which you can call all the methods you want.

## 1. Get a `MastodonApp`

Two options : if you already registered your app, then just use the constructor. If you did not register your app on the instance, use `MastodonApp.registerApp("mastodon.xyz", "MyGreatApp", "https://localhost:8080/", Scope.WRITE)` for example.

## 2. Login

To login, just call the `login` method of `MastodonApp`. It requires a redirectUri (that can be null) among all the redirectUris provided at app creation, and scopes.

This method returns a `LoginProcess` instance. This class offers two methods : `getLoginURL()` that gives you the URL you can give to your user, and `authorize(String code)` that will confirm the login and return a session once the code provided by Mastodon has been returned

## 3. Do stuff

You finally get a `MastodonSession`. It will in the future implement all the methods of the API directly in a proper Java way, but for now it's more than partial. But don't worry, you can still call directly the APIs using the `raw()` method.