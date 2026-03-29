import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    public void addCountry(Country country) throws Exception {
        String sql = "INSERT INTO countries (name, population) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getPopulation());
            preparedStatement.executeUpdate();
        }
    }

    public List<Country> getAllCountries() throws Exception {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM countries";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println(" Метадані таблиці ");
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + " (" + metaData.getColumnTypeName(i) + ") | ");
            }
            System.out.println("\n------------------------");

            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                country.setPopulation(rs.getInt("population"));
                countries.add(country);
            }
        }
        return countries;
    }

    public void updateCountry(Country country) throws Exception {
        String sql = "UPDATE countries SET name = ?, population = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, country.getName());
            pstmt.setInt(2, country.getPopulation());
            pstmt.setInt(3, country.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteCountry(int id) throws Exception {
        String sql = "DELETE FROM countries WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Country> searchCountriesByName(String keyword) throws Exception {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM countries WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getInt("id"));
                    country.setName(rs.getString("name"));
                    country.setPopulation(rs.getInt("population"));
                    countries.add(country);
                }
            }
        }
        return countries;
    }

}
