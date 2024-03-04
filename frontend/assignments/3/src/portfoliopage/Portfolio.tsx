
import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setTransactions, applyFilters, setLoading, setFilter, setFailedFilter, setStockFilter, setStartDate, setEndDate,toggleShowPassedTransactions, Transaction, } from '../Slices/transactionsSlice'; // Adjust the import path as necessary
import { RootState } from '../Slices/store'; 
import "./Portfolio.scss";

const Portfolio: React.FC = () => {
 const dispatch = useDispatch();
 const transactions = useSelector((state: RootState) => state.transactions.filteredTransactions);
 const loading = useSelector((state: RootState) => state.transactions.loading);
 const stockFilter = useSelector((state: RootState) => state.transactions.stockFilter);
 const showPassedTransactions = useSelector((state: RootState) => state.transactions.showPassedTransactions);

 useEffect(() => {
    const fetchTransactions = async () => {
      dispatch(setLoading(true));
      try {
        const response = await fetch('https://dev-1gyvfva3nqtb0v4.api.raw-labs.com/mock/portfolio-transactions');
        if (!response.ok) {
          console.error('Network response was not ok', response.status);
          return;
        }
        const data = await response.json();
        dispatch(setTransactions(data));
        dispatch(applyFilters()); 
      } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
      } finally {
        dispatch(setLoading(false));
      }
    };

    fetchTransactions();
 }, [dispatch]);

 
 const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setFilter(e.target.value));
    dispatch(applyFilters()); // Apply filters after changing the filter state
 };

 const handleFailedFilterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setFailedFilter(e.target.checked));
    dispatch(applyFilters()); // Apply filters after changing the filter state
 };


const handleStockFilterChange = (stockSymbol: string, checked: boolean) => {
  dispatch(setStockFilter({ ...stockFilter, [stockSymbol]: checked }));
  dispatch(applyFilters()); // Apply filters after changing the stock filter state
};

 const handleStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setStartDate(e.target.value));
    dispatch(applyFilters()); // Apply filters after changing the start date
 };

 const handleEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setEndDate(e.target.value));
    dispatch(applyFilters()); // Apply filters after changing the end date
 };
 const handleShowPassedTransactionsChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  dispatch(toggleShowPassedTransactions());
  dispatch(applyFilters()); 
};

 const resetFilters = () => {
    dispatch(setFilter(''));
    dispatch(setFailedFilter(false));
    dispatch(setStockFilter({}));
    dispatch(setStartDate(null));
    dispatch(setEndDate(null));
    dispatch(applyFilters()); 
 };

 interface GroupedTransactions {
  [date: string]: Transaction[];
 }
 
 const groupTransactionsByDate = (transactions: Transaction[]): GroupedTransactions => {
  return transactions.reduce((acc: GroupedTransactions, transaction: Transaction) => {
     const date = new Date(transaction.timestamp).toDateString(); // Convert to date string
     if (!acc[date]) {
       acc[date] = [];
     }
     acc[date].push(transaction);
     return acc;
  }, {});
 };
 


 return (
    <div className="portfolio-container">
     
<div className="filter-block">
    <div className="filter-clear">
        <div className="filter-text">Filter</div> 
        <div className="clear-text"><button className="reset-button" onClick={resetFilters}>Clear All</button></div>
    </div>

    <div className="search-bar">
        <input className="search-input" type="text" placeholder="Filter by stock name/symbol" onChange={handleFilterChange} />
    </div>
    <div className="date-inputs">
        <input type="date" onChange={handleStartDateChange} />
        <input type="date" onChange={handleEndDateChange} />
    </div>  
    <div className="pass-fail">
        <input type="checkbox" onChange={handleFailedFilterChange} />
        <label>failed transactions</label><br></br>
        <input type="checkbox" checked={showPassedTransactions} onChange={handleShowPassedTransactionsChange} />
        <label>passed transactions</label><br></br>
    </div>
    <div className="company-filter">
          {Array.from(new Set(transactions.map(transaction => transaction.stock_symbol))).map(stockSymbol => (
            <div key={stockSymbol}>
              <input
                type="checkbox"
                checked={stockFilter[stockSymbol]}
                onChange={(e) => handleStockFilterChange(stockSymbol, e.target.checked)}
              />
              <label>{stockSymbol}</label><br></br>
            </div>
          ))}
        </div>
</div>
         
{loading ? (
 <div>Loading...</div>
) : (
 transactions.length > 0 ? (
    <div className="transaction-list">
      {Object.entries(groupTransactionsByDate(transactions)).map(([date, transactions]) => (
        <div key={date}>
          
          <h2 className='dates'>{date}</h2>
          {transactions.map((transaction) => (
            <div key={transaction.stock_symbol} className="transaction-item">
              <div className="transaction-name"><h4>{transaction.stock_name} </h4></div>
              <div className="transaction-symbol"><p>{transaction.stock_symbol}</p></div>
              <div className="transaction-price"><p> {transaction.transaction_price}</p></div>
              <div className={`transaction-status ${transaction.status.toLowerCase()}`}>
        
        {transaction.status === "Passed" ? (
            <div className="circle passed"></div>
        ) : (
            <div className="circle failed"></div>
        )}
    </div>
            </div>
          ))}
        </div>
      ))}
    </div>
 ) : (
    <div>No transactions found.</div>
 )
)}

    </div>
 );
};

export default Portfolio;












