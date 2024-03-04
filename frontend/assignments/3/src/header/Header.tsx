import { useState } from "react"

import StocksList from "../stocklist/StockList"
import Watchlist from "../watchlist/Watchlist"

const bottomHeader:React.CSSProperties= {
    display:"flex",
    flexDirection:"row",
    width:"15%",
    justifyContent:"space-around",
    cursor: "pointer",
    marginTop: "15px",
    marginBottom: "15px",
}
const activeStyle:React.CSSProperties = {
    borderBottom: "2px solid #1871c2", 
    paddingBottom: "5px", 
   
};

const Header = () => {
    const [view, setView] = useState('stockList'); 
   
    const handleViewChange = (newView:any) => {
       setView(newView);
    };
   
    return (
       <>
         <div style={bottomHeader}>
         <h4 style={view === 'stockList' ? activeStyle : {}} onClick={() => handleViewChange('stockList')}>Explore</h4>
                <h4 style={view === 'watchlist' ? activeStyle : {}} onClick={() => handleViewChange('watchlist')}>My WatchList</h4>
         </div>
         {view === 'stockList' && <StocksList />}
         {view === 'watchlist' && <Watchlist />}
       </>
    );
   };

export default Header;