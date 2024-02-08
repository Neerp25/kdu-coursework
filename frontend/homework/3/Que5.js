function outputKeysAndValues(obj) {
    
    const keys = Object.keys(obj);
    console.log("All Keys:", keys);
  
    const values = Object.values(obj);
    console.log("All Values:", values);
  
    for (const key in obj) {
      if (typeof obj[key] === 'object' && obj[key] !== null) {
        outputKeysAndValues(obj[key]);
      }
    }
  }

  const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
      country: "Spain",
      city: "Barcelona",
    },
    careerInfo: {
      fcBarcelona: {
        appearances: 780,
        goals: {
          premierLeagueGoals: 590,
          championsLeagueGoals: 50,
        },
      },
    },
  };
  outputKeysAndValues(player);