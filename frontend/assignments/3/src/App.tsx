
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { Provider } from 'react-redux';

import { store } from './Slices/store';
import Header from './header/Header';

import './App.css';
import Portfolio from './portfoliopage/Portfolio';
import StockMarketDashboard from './StockPage/StockMarketDashboard';
import Topsection from './Topsection';
 
function App() {
 return ( 
    <Provider store={store}>
      <Router>
        <div id="main-container">
          <Topsection/>
          <Routes>
            <Route path="/" element={<Header/>} />
            <Route path="/profile" element={<Portfolio/>} />
            <Route path="/stock/:id" element={<StockMarketDashboard/>}/>
          </Routes>
        </div>
      </Router>
    </Provider>
 );
}

export default App;


