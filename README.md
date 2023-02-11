# fibonacciApp

## App created to present fibonacci series values (gives n'th value of a series) and allows to display history of requests (with dates and requested values and results)

## Development decisions:

- Memoization algorithm -> in the first stage of development recurrent approach was implemented,
  however this soultion is very computational heavy, causes ANR's.
  There was a trial to wrap it inside courutine and run it in other thread but this approach worked
  only up until the 50th element. So after some research the memoization
  algorithm was picked because of the cache mechanism -> basically the mechanism allows to cache
  return values of the function responsible for calculating the fibonacci series
  values, so it returns precomputed values very quick.

- Usage of BigInteger as a return value in function calculating fibonacci values -> as the goal of
  this assignment was to use arbitrary numbers the decision was made to use BigInteger because those
  are described in the docs as
  ''Immutable arbitrary-precision integers'', also as this project was an experiment for me I
  implemented this as Int and as Long on the way, only then I realised that it's not going to be
  enough if bigger numbers will be requested.

- Uage of DataStore instead of SharedPreferences to keep data between sessions -> after reading this
  article https://android-developers.googleblog.com/2020/09/prefer-storing-data-with-jetpack.html
  basically datastore is replacing drawbacks shared preferences had

- Filtering of input -> to avoid wrong input types (using isDigitOnly) simple, clean solution

- Design -> as simple and as functional as possible with a hope that the overall app is intuitive

- Toast message on wrong input -> wrong input (e.g. letters, special signs, spaces) replaces the
  input under the hoood ot number 1 for simplicity

- Extension functions -> to centralize the code better and reuse methods for converting json strings
  to lists

##Screenshots of the app
|![image](https://user-images.githubusercontent.com/56221715/217261387-0293d344-44cc-4812-8a98-58c0763a8c1d.png)
|![image](https://user-images.githubusercontent.com/56221715/217261638-f366f4e6-396a-4661-aec2-1ee89991751b.png)
|
|----|---|
|![image](https://user-images.githubusercontent.com/56221715/217261828-1aee8724-773c-4657-840f-bb6c9fd50623.png)
|![image](https://user-images.githubusercontent.com/56221715/217261914-d6af1221-d260-4391-a701-fc72ec7f3028.png)
|
