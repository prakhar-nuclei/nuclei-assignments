package com.nuclei.assignment3.repo;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemTypeEnum;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

@Repository
public class ItemRepository {

    private static final String FIND_ITEMS_SQL = """ 
             SELECT name, price, quantity, type
             FROM items
             ORDER BY id
            """;

    private final DataSource dataSource;

    public ItemRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void readItems(Consumer<Item> itemConsumer) throws SQLException {  // consumer functional interface
                                                                              //  accepting the item from db
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ITEMS_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getInt("quantity"),
                        ItemTypeEnum.valueOf(resultSet.getString("type"))
                );

                itemConsumer.accept(item);
            }
        }
    }
}
