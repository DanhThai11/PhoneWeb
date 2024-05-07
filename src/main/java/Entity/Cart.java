package Entity;

public class Cart {
	private int accountId;
	private int productId;
	private int amount;

	public Cart(int accountId, int productId, int amount) {
		this.accountId = accountId;
		this.productId = productId;
		this.amount = amount;
	}

	public Cart(){
	} 
	
	public int getaccountId() {
		return accountId;
	}

	public int getProductId() {
		return productId;
	}

	public int getamount() {
		return amount;
	}

	public void setaccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setamount(int amount) {
		this.amount = amount;
	}
	public String toString() {
		return "Cart [accountId=" + accountId + ", productId=" + productId + ", amount=" + amount + "]";
	}
}
