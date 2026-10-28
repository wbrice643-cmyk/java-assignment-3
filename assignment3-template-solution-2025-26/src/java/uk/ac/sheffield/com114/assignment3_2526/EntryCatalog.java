package uk.ac.sheffield.com114.assignment3_2526;

import uk.ac.sheffield.com114.assignment3_2526.codeprovided.*;

import java.io.IOException;
import java.util.*;

// This class is used to represent an Entry catalog containing all the entries in the dataset.
// This class provides a non-operational basic implementation of it.
// You will need to change this class A LOT, meaning adding imports, variables, and methods!
// You will need to adequately document this class, including JavaDoc documentation, relevant comments
// You will need to remove these starting template comments provided to help your programming task but irrelevant
// to the programming task
// You will need to remove the TODO comments (if any)
// You will need to remove unused code
// You will need to remove unused variables
// You will need to use adequate naming...

public class EntryCatalog extends AbstractEntryCatalog {

    /**
     * Constructor will send the entry file to the abstractEntryCatalog's
     * constructor where it will call a function to make entriesList.
     * @param entryFile file to be added to catalog
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public EntryCatalog(String entryFile)
            throws IllegalArgumentException, IOException {
        super(entryFile);
    }

    /**
     * Public function which turns a sting into a list
     * which can be stored in the entriesList.
     * @param line the string to be turned into a list
     * @return List.of(entryLine) the line turned into a list
     * @throws IllegalArgumentException
     */
    @Override
    public List<String> parseEntryLine(String line) throws IllegalArgumentException {
        String[] entryLine = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        if (entryLine.length != 8) {//(EntryDetail.values().length + EntryProperty.values().length)) {

            throw new IllegalArgumentException("Inadequate file format. Provide a valid dataset.");
        }
        return List.of(entryLine);
    }

    /**
     * Function that gets the number of entries.
     * @return entriesList.size()
     */
    @Override
    public int getNumberOfEntries() {
        return entriesList.size();
    }

    /**
     * A function that returns the average imdb score
     * between 2 years.
     * @param startYear the first year included
     * @param endYear the last year included
     * @return total / count average between 2 years
     */
    @Override
    public double getAverageImdbScoreTitlesBetweenYears(int startYear, int endYear) {
        double total = 0;
        int count = 0;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getYear() >= startYear && e.getYear() <= endYear) {
                total += e.getImdbRating();
                count++;
            }
        }
        return total / count;
    }

    /**
     * Function that gets an average imdb score for movies before a given year.
     * @param year
     * @return total / count an average of movies released before year
     */
    @Override
    public double getAverageImdbScoreTitlesReleasedBeforeYear(int year) {
        double total = 0;
        int count = 0;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getYear() < year) {
                total += e.getImdbRating();
                count++;
            }
        }
        return total / count;
    }

    /**
     * Function that gets an average imdb score for movies after a given year.
     * @param year
     * @return total / count an average of movies released before year
     */
    @Override
    public double getAverageImdbScoreTitlesReleasedAfterYear(int year) {
        double total = 0;
        int count = 0;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getYear() > year) {
                total += e.getImdbRating();
                count++;
            }
        }
        return total / count;
    }

    /**
     * A function that returns the minimum imdb score
     * for a given type of content.
     * @param type the given type of content
     * @return minimum
     */
    @Override
    public double getMinimumImdbScoreTitle(Type type) {
        double minimum = 100;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getImdbRating() < minimum && e.getType() == type) {
                minimum = e.getImdbRating();
            }
        }
        return minimum;
    }

    /**
     * A function that returns the maximum imdb score
     * for a given type of content.
     * @param type the given type of content
     * @return maximum
     */
    @Override
    public double getMaximumImdbScoreTitle(Type type) {
        double maximum = -1;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getImdbRating() > maximum && e.getType() == type) {
                maximum = e.getImdbRating();
            }
        }
        return maximum;
    }

    /**
     * A function that returns the average imdb score
     * for a given type of content.
     * @param type the given type of content
     * @return total / count
     */
    @Override
    public double getAverageImdbScoreTitle(Type type) {
        double total = 0;
        int count =0;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if ( e.getType() == type) {
                total += e.getImdbRating();
                count++;
            }
        }
        return total / count;
    }

    /**
     * A function that returns a map containing 2 keys
     * which are MOVIE and SHOW which each a value of
     * the set of all true story titles which are shows
     * or movies.
     * @return titlesBasedOnTrueStory
     */
    @Override
    public Map<String, Set<String>> getTitlesBasedOnTrueStory() {
        Map<String, Set<String>> titlesBasedOnTrueStory = new HashMap<>();
        Set<String> movieTitles = new HashSet<>();
        Set<String> showTitles = new HashSet<>();
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getDescription().contains("true story") || e.getDescription().contains("true events") || e.getDescription().contains("real events")){
                if(e.getType() == Type.MOVIE) {
                    movieTitles.add(e.getTitle());
                }
                else {
                    showTitles.add(e.getTitle());
                }
            }
        }
        titlesBasedOnTrueStory.put("MOVIE",movieTitles);
        titlesBasedOnTrueStory.put("SHOW",showTitles);
        return titlesBasedOnTrueStory;
    }

    /**
     * A function which compares the imdb scores of the entries
     * based off of an operator and score given to it then
     * returns the number of entries that have that imdb score.
     * @param score the score to compare to
     * @param operator the type of operator to use
     * @return noTitles
     */
    @Override
    public int getNoTitlesImdbScore(double score, Operator operator) {
        int noTitles = 0;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            switch (operator.getOperator()) {
                case "==": {
                    if (e.getImdbRating() == score) {
                        noTitles++;
                    }
                    break;
                }
                case "!=": {
                    if (e.getImdbRating() != score) {
                        noTitles++;
                    }
                    break;
                }
                case ">": {
                    if (e.getImdbRating() > score) {
                        noTitles++;
                    }
                }
                case "<": {
                    if (e.getImdbRating() < score) {
                        noTitles++;
                    }
                }
                case ">=": {
                    if (e.getImdbRating() >= score) {
                        noTitles++;
                    }
                }
                case "<=": {
                    if (e.getImdbRating() <= score) {
                        noTitles++;
                    }
                }
            }
        }
        return noTitles;
    }

    /**
     * Function that gets the average run time for entries with a
     * given ager certificate
     * @param certification the given age certificate
     * @return total / count the average run time
     */
    @Override
    public double getAverageRuntimeRatedAgeCertification(Certification certification) {
        double total = 0;
        int count = 0;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getAgeCertification() == certification) {
                total += e.getRuntime();
                count++;
            }
        }
        return total / count;
    }

    /**
     * Function that gets the minimum run time
     * for a given age certificate.
     * @param certification given age certificate
     * @return minimum
     */
    @Override
    public double getMinRuntimeRatedAgeCertification(Certification certification) {
        double minimum = 100;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getImdbRating() < minimum && e.getAgeCertification() == certification) {
                minimum = e.getRuntime();
            }
        }
        return minimum;
    }

    /**
     * Function that gets the maximum run time
     * for a given age certificate.
     * @param certification given age certification
     * @return maximum
     */
    @Override
    public double getMaxRuntimeRatedAgeCertification(Certification certification) {
        double maximum = -1;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getImdbRating() > maximum && e.getAgeCertification() == certification) {
                maximum = e.getRuntime();
            }
        }
        return maximum;
    }


    /**
     * A function that gets the earliest release year
     * @return earliestYear
     */
    @Override
    public int getEarliestReleaseYear() {
        int earliestYear = 2026;
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if (e.getYear() < earliestYear) {
                earliestYear = e.getYear();
            }
        }
        return earliestYear;
    }

    /**
     * A function that gets all documentaries
     * @return documentaries
     */
    @Override
    public List<String> getDocumentaries() {
        List<String> documentaries = new ArrayList<>();
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getDescription().contains("documentary")){
                documentaries.add(e.getTitle());
            }
        }
        return documentaries;
    }

    /**
     * A function that gets all movies released in
     * the last 5 years.
     * @return movies
     */
    @Override
    public List<String> getLastFiveYearsMovies() {
        final int YEAR = 2025;
        List<String> movies = new ArrayList<>();
        for (AbstractEntry abstractEntry : entriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getYear() >= YEAR-5 && e.getType() == Type.MOVIE){
                movies.add(e.getTitle());
            }
        }
        return movies;
    }

}
