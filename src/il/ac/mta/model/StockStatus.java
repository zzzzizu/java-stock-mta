package il.ac.mta.model;

import java.util.Date;
import il.ac.mta.model.Portfolio.ALGO_RECOMMENDATION;

public class StockStatus extends Stock 
{
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	public StockStatus(Stock stock, ALGO_RECOMMENDATION recommendation1, int stockQuantity1) 
	{
		super(stock);
		this.setRecommendation(recommendation1);
		this.setStockQuantity(stockQuantity1);
	}
	
	// copy c'tor
	public StockStatus(StockStatus stockStatus)
	{
		super(stockStatus.symbol, stockStatus.ask, stockStatus.bid, stockStatus.date);
		this.recommendation = stockStatus.recommendation;
		this.stockQuantity = stockStatus.stockQuantity;
	}
	
	public ALGO_RECOMMENDATION getRecommendation() 
	{
		return recommendation;
	}
	
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) 
	{
		this.recommendation = recommendation;
	}
	
	public int getStockQuantity() 
	{
		return stockQuantity;
	}
	
	public void setStockQuantity(int stockQuantity) 
	{
		this.stockQuantity = stockQuantity;
	} 
}
