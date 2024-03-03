
import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { RootState, AppDispatch } from '../Slices/store';
import { setStocks, addToWishlist } from '../Slices/stockSlice';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import "./StockList.css";


interface Stock {
 id: string;
 name: string;
 price: number;
}

const StocksList: React.FC = () => {
 const dispatch = useDispatch<AppDispatch>();
 const allStocks = useSelector((state: RootState) => state.stocks.stocks);
 const [currentPage, setCurrentPage] = useState(1);
 const itemsPerPage = 10;

 useEffect(() => {
    const fetchStocks = async () => {
      try {
        const response = await fetch('https://dev-6hy1cukrs46j7f1.api.raw-labs.com/mock/json-api');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        // Map API data to the expected structure
        const mappedData: Stock[] = data.map((item: any) => ({
          id: item.stock_symbol, 
          name: item.stock_name,
          price: item.base_price,
        }));
        dispatch(setStocks(mappedData));
      } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
      }
    };

    fetchStocks();
 }, [dispatch]);

 const handleChange = (event: React.ChangeEvent<unknown>, value: number) => {
    setCurrentPage(value);
 };

 const handleAddToWishlist = (stock: Stock) => {
    dispatch(addToWishlist(stock));
 };

 
 const indexOfLastItem = currentPage * itemsPerPage;
 const indexOfFirstItem = indexOfLastItem - itemsPerPage;
 const currentItems = allStocks.slice(indexOfFirstItem, indexOfLastItem);

 return (
  <div className="container">
  <div className="header">
    <h4>Company</h4>
    <div className="header-details">
    <h4>BasePrice</h4>
    <h4 className="wishlist-label">Wishlist</h4>
    </div>

  </div>
  <div className="stock-item-container">
    {currentItems.map((stock) => (
      <div key={stock.id} className="stock-item">
        <h4>{stock.id}</h4>
        <div className="stock-item-price-container">
          
          <h4 className="stock-price-spacing">{stock.price}</h4>
          
            <span className="material-symbols-outlined" onClick={() => handleAddToWishlist(stock)}>
             add_circle
            </span>

        </div>
      </div>
    ))}
  </div>
  <Stack spacing={2}>
    <Pagination count={Math.ceil(allStocks.length / itemsPerPage)} page={currentPage} onChange={handleChange} />
  </Stack>
</div>


 );
};

export default StocksList;





