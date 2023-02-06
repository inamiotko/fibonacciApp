# fibonacciApp
## App created to present fibonacci series values (gives n'th value of a series) and allows to display history of requests (with dates and requested values and results)

## Development decisions:
- Memoization algorithm -> in the first stage of development recurrent approach was implemented, however this soultion is very computational heavy, causes ANR's. 
There was a trial to wrap it inside courutine and run it in other thread but this approach worked only up until the 50th element. So after some research the memoization 
algorithm was picked because of the cache mechanism -> basically the mechanism allows to cache return values of the function responsible for calculating the fibonacci series
values, so it returns precomputed values very quick.

- Usage of BigInteger as a return value in function calculating fibonacci values -> as the goal of this assignment was to use arbitrary numbers the decision was made to use BigInteger because those are described in the docs as
''Immutable arbitrary-precision integers'', also as this project was an experiment for me I implemented this as Int and as Long on the way, only then I realised that it's 
not going to be enough if bigger numbers will be requested.

- Uage of DataStore instead of SharedPreferences to keep data between sessions -> after reading this article https://android-developers.googleblog.com/2020/09/prefer-storing-data-with-jetpack.html
basically datastore is replacing drawbacks shared preferences had

- Filtering of input -> to avoid wrong input types (using isDigitOnly) simple, clean solution.


