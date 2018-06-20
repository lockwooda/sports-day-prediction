# Sports Day Predictions
This repository contains my A Level coursework, which was the handling and processing of my secondary school's house competitions.

Previously, all data was kept within flatfile spreadsheets and was opaque to the PE department. The core idea behind this was to make it much easier for the PE department to be able to handle the data and have it such that all the core mathematics is done in the background.

## Predictions
A predictions system was added in to allow for the PE department to predict who the winners of the current year would be. This prediction is based upon data from previous years and a bodge of the [Elo rating system](https://en.wikipedia.org/wiki/Elo_rating_system).

Each house in each event is given a rating based upon its previous performance, with the probabilities of the rating being used in order to determine who is most likely to come first, second, and so on. This is then extrapolated, with the predicted points then being added up in order to determine the most likely winner of the year-long sports competitions based upon data from previous years.
