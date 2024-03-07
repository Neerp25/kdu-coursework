import { useContext } from "react";
import { TodoContext } from "./Todolist"


function Header() {
  const {searchTerm, setSearchTerm}= useContext(TodoContext);
  return (
    <div  id ="header">
      <h2>Item Lister</h2>
      <div>
      <input id="searchbox"
        type="text"
        placeholder="Search..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />  
      </div>
    </div>
  )
}

export default Header