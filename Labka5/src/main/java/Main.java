import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();
            CountryDAO countryDAO = new CountryDAO();

            String username = "admin";
            String password = "super_password";

            userDAO.registerUser(username, password);

            boolean isAuthenticated = userDAO.loginUser(username, password);
            System.out.println("Is user authenticated? " + isAuthenticated);

            if (isAuthenticated) {
                Country country1 = new Country(0, "Ukraine", 38000000);
                Country country2 = new Country(0, "Japan", 125000000);

                countryDAO.addCountry(country1);
                countryDAO.addCountry(country2);

                List<Country> countries = countryDAO.getAllCountries();
                for (Country c : countries) {
                    System.out.println(c.getId() + " - " + c.getName() + " - " + c.getPopulation());
                }

                List<Country> foundCountries = countryDAO.searchCountriesByName("Jap");
                for (Country c : foundCountries) {
                    System.out.println("Found: " + c.getName());
                }

                if (!countries.isEmpty()) {
                    Country toUpdate = countries.get(0);
                    toUpdate.setPopulation(39000000);
                    countryDAO.updateCountry(toUpdate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}