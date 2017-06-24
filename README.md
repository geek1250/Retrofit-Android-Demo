# Retrofit-Android-Demo

This is demo of using Retrofit for Android.

Retrofit is a type-safe HTTP client for Android and Java, it also can be used for calling restful APIs.

Definination:

public interface CommitApiService {

   @GET("search/commits")
   
    Call<ItemResponse> getReposCommits( @Header("Accept") String authorization, @Query(value="q", encoded = false) String para);

}


Usage:

public static final String BASE_URL = "https://api.github.com/";
    
private static Retrofit retrofit = null;
    
private RecyclerView recyclerView = null;

private final static String REPO_PATH = "repo:square/retrofit merge:false sort:author-date-desc";
    
private final static String HEADER_AUTH = "application/vnd.github.cloak-preview";



if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

CommitApiService commitApiService = retrofit.create(CommitApiService.class);

Call<ItemResponse> call = commitApiService.getReposCommits(HEADER_AUTH,REPO_PATH);

