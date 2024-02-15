interface Recipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    timetaken: number;
    calorieCount: number;
  }

  class RecipeSearchApp{

    private recipes:Recipe[]=[];

    private async fetchRecipesFromAPI() {
        try {
          const response = await fetch('https://dummyjson.com/recipes');
          const data = await response.json();
        this.recipes = data.recipes.map((recipeData: any) => ({
            image: recipeData.image,
            name: recipeData.name,
            rating: recipeData.rating,
            cuisine: recipeData.cuisine,
            ingredients: recipeData.ingredients,
            difficulty: recipeData.difficulty,
            timetaken:recipeData.cookTimeMinutes + recipeData.prepTimeMinutes,
            calorieCount: recipeData.caloriesPerServing,
          }));


          console.log('Recipes fetched from API:', this.recipes);
        } catch (error) {
          console.error('Error fetching recipes from API:', error);
        }
      }

      private async searchRecipes(query: string) {
        const response = await fetch(`https://dummyjson.com/recipes/search?q=${query}`);
        const searchData = await response.json();

        this.recipes = searchData.recipes.map((recipeData: any) => ({
            image: recipeData.image,
            name: recipeData.name,
            rating: recipeData.rating,
            cuisine: recipeData.cuisine,
            ingredients: recipeData.ingredients,
            difficulty: recipeData.difficulty,
            timetaken:recipeData.cookTimeMinutes + recipeData.prepTimeMinutes,
            calorieCount: recipeData.caloriesPerServing,
          }));

        console.log(`Search results for '${query}':`, this.recipes);
      }

      private printAllRecipes(): void {
        console.log('Type of recipes:', typeof this.recipes);

        // Check if this.recipes is an array
        if (Array.isArray(this.recipes)) {
          this.recipes.forEach((recipe, index) => {
            console.log(`Image: ${recipe.image}`);
            console.log(`Name: ${recipe.name}`);
            console.log(`Rating: ${recipe.rating}`);
            console.log(`Cuisine: ${recipe.cuisine}`);
            console.log(`Ingredients: ${recipe.ingredients.join(', ')}`);
            console.log(`Difficulty: ${recipe.difficulty}`);
            console.log(`Time Taken: ${recipe.timetaken} minutes`);
            console.log(`Calorie Count: ${recipe.calorieCount}`);

            console.log('\n');
          });
        } else {
          console.error('Recipes is not an array:', this.recipes);
        }
      
    }
    
    public async run() {
        await this.fetchRecipesFromAPI();
        await this.searchRecipes('chicken');
        this.printAllRecipes();
    }

  }

  const app = new RecipeSearchApp();
  app.run();