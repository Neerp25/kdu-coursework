// HeaderComponent.tsx
import React from 'react';

interface HeaderComponentProps {
  searchTerm: string;
  handleSearchChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  selectedBrand: string;
  handleBrandChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
  sortOrder: string;
  handleSortChange: () => void;
}

const HeaderComponent: React.FC<HeaderComponentProps> = ({
  searchTerm,
  handleSearchChange,
  selectedBrand,
  handleBrandChange,
  sortOrder,
  handleSortChange,
}) => {
  return (
    <div id="Header">
      <input
        id="Searchbox"
        type="text"
        placeholder="Search by title..."
        value={searchTerm}
        onChange={handleSearchChange}
      />

      {/* Brand Filter Dropdown */}
      <div id="lable">
        <label id="lable" htmlFor="brandFilter">
          Filter:
        </label>
      </div>
      <select id="brandFilter" value={selectedBrand} onChange={handleBrandChange}>
        <option value="All">All Brands</option>
        <option value="Men's Clothing">Men's Clothing</option>
        <option value="Women's Clothing">Women's Clothing</option>
        <option value="jewelery">Jewelry</option>
        <option value="Electronics">Electronics</option>
      </select>

      {/* Sort Order Buttons */}
      <div id="sort-btn">
        <button onClick={handleSortChange}>
          Sort Price: {sortOrder === 'asc' ? 'Asc' : 'Des'}
        </button>
      </div>
    </div>
  );
};

export default HeaderComponent;
