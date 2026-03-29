import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    public void addCity(City city) throws Exception {
        String sql = "INSERT INTO cities (name, country_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, city.getName());
            pstmt.setInt(2, city.getCountryId());

            pstmt.executeUpdate();
        }
    }


    public List<City> getAllCities() throws Exception {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM cities";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setCountryId(rs.getInt("country_id")); // Зверни увагу: тут назва колонки з БД
                cities.add(city);
            }
        }
        return cities;
    }

    public void updateCity(City city) throws Exception {
        String sql = "UPDATE cities SET name = ?, country_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, city.getName());
            pstmt.setInt(2, city.getCountryId());
            pstmt.setInt(3, city.getId());
            pstmt.executeUpdate();
        }
    }


    public void deleteCity(int id) throws Exception {
        String sql = "DELETE FROM cities WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
