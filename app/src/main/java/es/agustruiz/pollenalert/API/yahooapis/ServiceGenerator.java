package es.agustruiz.pollenalert.api.yahooapis;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ServiceGenerator {

    public static final String API_BASE_URL = "https://your.api-base.url";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        // we shortened this part, because it’s covered in
        // the previous post on basic authentication with Retrofit
    }

    public static <S> S createService(Class<S> serviceClass, AccessToken token) {
        if (token != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json")
                    request.addHeader("Authorization",
                            token.getTokenType() + " " + token.getAccessToken());
                }
            });
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
