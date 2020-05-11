package gameLogic;

import objects.Rating;

/**
 * Rating system calculation class
 */
public class RatingHandler {

    /**
     * All existing ratings
     */
    private enum rating{
        WOOD(new Rating("./rangs/First_rang.png", "Wood", 1)),
        STEEL(new Rating("./rangs/Second_rang.png", "Steel", 2));

        private final Rating rating;

        rating(Rating rating) {
            this.rating = rating;
        }

        public Rating getRating() {
            return rating;
        }
    }


    /**
     * Get current rating based on {@param ratingValue}
     */
    public static Rating getCurrentRating(int ratingValue) {
        return rating.values()[calculateGradation(ratingValue)].getRating();
    }

    /**
     * Get number of rating based on special computing form {@link rating}
     */
    private static int calculateGradation(int ratingValue){
        return ratingValue / 150 / 5;
    }

    /**
     * Get progress of rating based on special computing
     */
    public static double getProgress(double ratingValue){
        return ratingValue % 750 / 750;
    }
}
