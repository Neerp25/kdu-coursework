import React, { useEffect, useRef } from 'react';

function ScrollToTop() {
  
  const buttonRef = useRef<HTMLButtonElement | null>(null);
  const el = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    
    if (el.current) {
      el.current.scrollIntoView({ behavior: 'smooth' });
    }
  }, []); 

  return (
    <div>
      <div ref={el}>This is the target element to scroll to.....</div>
      <button ref={buttonRef} onClick={() => el.current?.scrollIntoView({ behavior: 'smooth' })}>
        Scroll to Top
      </button>
    </div>
  );
}
export default ScrollToTop

