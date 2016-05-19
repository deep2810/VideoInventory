package videostore;

public abstract class AbstractInventoryDecorator extends AbstractInventory {

    protected AbstractInventory inventory;

    @Override
    public float getPrice(String movieName) {
        return inventory.getPrice(movieName);
    }

    @Override
    public float getPrice(int movieId) {
        return inventory.getPrice(movieId);
    }

    @Override
    public int getQuantity(String movieName) {
        return inventory.getQuantity(movieName);
    }

    @Override
    public int getQuantity(int movieId) {
        return inventory.getQuantity(movieId);
    }

}