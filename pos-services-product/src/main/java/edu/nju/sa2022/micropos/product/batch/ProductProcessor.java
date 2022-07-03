package edu.nju.sa2022.micropos.product.batch;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.sa2022.micropos.models.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<JsonNode, Product> {

    @Override
    public Product process(JsonNode jsonNode) throws Exception {
        if (!jsonNode.has("asin") ||
                !jsonNode.has("title") ||
                !jsonNode.has("price") ||
                !jsonNode.has("imageURL")) {
            return null;
        }
        String id = jsonNode.get("asin").textValue();
        String name = jsonNode.get("title").textValue();

        String priceStr = jsonNode.get("price").textValue();
        if (priceStr.startsWith("$")) {
            priceStr = priceStr.substring(1);
        }
        if (priceStr.contains(" ")) {
            priceStr = priceStr.substring(0, priceStr.indexOf(" "));
        }
        priceStr = priceStr.replace(",", "");
        if (priceStr.isEmpty() || !Character.isDigit(priceStr.charAt(0))) {
            return null;
        }
        double price = Double.parseDouble(priceStr);

        JsonNode imageURL = jsonNode.get("imageURLHighRes");
        String image = null;
        if (imageURL.isArray() && imageURL.size() > 0) {
            image = imageURL.get(0).textValue();
        }
        return new Product(id, name, price, image);
    }

}
