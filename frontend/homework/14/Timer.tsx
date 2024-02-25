import React, { useState, useEffect, useRef } from 'react';

function Timer() {
  const [seconds, setSeconds] = useState(0);
  const timerRef = useRef<number | null>(null); // Corrected line

  useEffect(() => {
    timerRef.current = window.setInterval(() => {
      setSeconds(seconds => seconds +  1);
    },  1000);

    return () => {
      if (timerRef.current !== null) {
        window.clearInterval(timerRef.current);
      }
    };
  }, []);

  return (
    <div>
      <p>Seconds: {seconds}</p>
    </div>
  );
}

export default Timer
