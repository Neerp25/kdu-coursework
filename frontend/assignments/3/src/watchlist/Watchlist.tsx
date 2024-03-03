import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { removeFromWishlist } from '../Slices/stockSlice';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import "./Watchlist.css";


interface Stock {
    id: string;
    name: string;
    price: number;
}

function Watchlist() {
    const wishlist = useSelector((state: any) => state.stocks.wishlist);
    const dispatch = useDispatch();

    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 10;

    const handleChange = (event: React.ChangeEvent<unknown>, value: number) => {
        setCurrentPage(value);
    };

    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = wishlist.slice(indexOfFirstItem, indexOfLastItem);

    const handleRemove = (stockId: string) => {
        dispatch(removeFromWishlist(stockId));
    };

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
                {currentItems.map((stock: Stock) => (
                    <div key={stock.id} className="stock-item">
                        <h4>{stock.id}</h4>
                        <div className="stock-item-price-container">
                            
                            <h4 className="stock-price-spacing">{stock.price}</h4>
                            
                           <span className="material-symbols-outlined" id ="material-symbols-outlined-x" onClick={() => handleRemove(stock.id)}>
                            close
                           </span>
                        </div>
                    </div>
                ))}
            </div>
            <Stack spacing={2}>
                <Pagination count={Math.ceil(wishlist.length / itemsPerPage)} page={currentPage} onChange={handleChange} />
            </Stack>
        </div>
    );
}

export default Watchlist;



