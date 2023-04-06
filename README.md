This simple app allows for addition of a car object to a list of cars in a car park.
The max number of cars is a variable in the file but could easily be set in parameters or come from a settings file etc.

The parkCar and carLeaves method are synchronised for stream safety
Even though List is not threadsafe vector is much slower so if collation is needed then
streams.collect() might be a suitable option to use.

A basic set of tests have been added, JUnit 5 has been selected

Optional has been used for a potentially empty car park rather than null
Optional has also been used for the timecarleaves as this is unknown at car creation time and should only be set when the car is removed from the car park


LocaldateTime has been used for time calculation.
It's assumed that if a car leaves the car park immediately then the minimum charge of 1 hour should apply
