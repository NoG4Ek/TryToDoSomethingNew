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
        NULL(new Rating("./rangs/Null_rang.png", "Null", 0)),
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
        for (int i = 0; i < rating.values().length; i++) {
            if (rating.values()[i].getRating().getNumber() == calculateGradation(ratingValue) + 1)
                return rating.values()[i].getRating();
        }
        return rating.NULL.getRating();
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
