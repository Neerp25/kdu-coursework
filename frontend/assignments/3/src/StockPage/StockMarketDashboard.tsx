// StockMarketDashboard.tsx
import React, { useState, useEffect } from 'react';
import { io } from 'socket.io-client';
import "./StockMarketDashboard.scss";
import { useParams } from 'react-router-dom';

interface Transaction {
  type: string;
  amount: number;
  time: string;
}

const StockMarketDashboard: React.FC = () => {
  const [price, setPrice] = useState<number>(0);
  const [prices, setPrices] = useState<number[]>([]);
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [quantity, setQuantity] = useState<number>(0);
  const { id } = useParams<{ id: string }>();
  if (!id) {
    return null; 
   }
  useEffect(() => {
    const socket = io("http://localhost:5000", {
      withCredentials: true,
      extraHeaders: {
        "my-custom-header": "abcd"
      }
    });

    fetchAndSetData();

    const intervalId = setInterval(() => {
      fetchAndSetData();
    }, 5000);

    socket.on('priceUpdate', (newPrice: number) => {
      setPrice(newPrice);
      setPrices(prevPrices => [...prevPrices, newPrice]);
    });

    return () => {
      clearInterval(intervalId);
      socket.disconnect();
    };
  }, []);

  useEffect(() => {
    console.log("Price updated:", price);
   
  }, [price]);

  const fetchAndSetData = () => {
    fetch("http://localhost:5000/statup/data")
      .then(response => response.json())
      .then(data => {
        const [updatedPrices, updatedTransactions] = data;
        setPrice(updatedPrices[0]);
        setPrices(prevPrices => [...prevPrices, ...updatedPrices]);
        setTransactions(updatedTransactions);
      })
      .catch(error => console.error('Error fetching data:', error));
  };

  const handleTransaction = (type: string, amount: number) => {
    const transaction = { type, amount, time: new Date().toISOString() };
    fetch('http://localhost:5000/transaction/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(transaction),
    })
      .then(response => response.json())
      .then(data => {
        console.log('Transaction added:', data);
        setTransactions(prevTransactions => [transaction, ...prevTransactions]);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  const handleBuy = () => {
    handleTransaction("Buy", quantity);
  };

  const handleSell = () => {
    handleTransaction("Sell", quantity);
  };

  return (
    <section className="main-container">
      <div className="left-main">
        <div className="company-info">    
          <div className="midsection">
          <div className="logo-container">
                       <h2>{id.substring(0,3)}</h2>
                        <h2>{id.substring(3)}</h2>
                    </div>
            <form onSubmit={(e) => e.preventDefault()}>
              <div className="buy-sell-container">
                <div className='CurrentPrice'>Current Price: {price}</div>
                <input
                  type="number"
                  value={quantity}
                  onChange={(e) => setQuantity(Number(e.target.value))}
                  className="quantity-input"
                />
                <button className="buy-btn" onClick={handleBuy}>Buy</button>
                <button className="sell-btn" onClick={handleSell}>Sell</button>
              </div>
            </form>
          </div>
        </div>
        <div className="graph-container">
          <div className="bar-graph-container">
            {prices.map((currentPrice, index) => (
              <div
                key={index}
                className={`bar-graph ${currentPrice >= (index > 0 ? prices[index - 1] : 0) ? 'up' : 'down'}`}
                style={{ height: `${currentPrice}px` }}
              ></div>
            ))}
          </div>
        </div>
      </div>
      <div className="right-main">
        <h1>History</h1>
        <ul>
          {transactions.map((transaction, index) => (
            <li key={index}>{transaction.type}: {transaction.amount} at {transaction.time}</li>
          ))}
        </ul>
      </div>
    </section>
  );
};

export default StockMarketDashboard;










