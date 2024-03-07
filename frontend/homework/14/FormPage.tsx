import React, { useEffect, useRef } from 'react';

function FormPage() {
  
  const inputRef = useRef<HTMLInputElement | null>(null);

  useEffect(() => {
    
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, []);

  return (
    <form>
      <input ref={inputRef} type="text" placeholder="First input" />
      
    </form>
  );
}

export default FormPage