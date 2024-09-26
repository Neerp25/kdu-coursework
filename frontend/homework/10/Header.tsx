interface HeaderProps {
    searchTerm: string;
    setSearchTerm: (value: string) => void;
  }

function Header({searchTerm, setSearchTerm}:HeaderProps) {
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


