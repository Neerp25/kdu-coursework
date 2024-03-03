var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
class RecipeSearchApp {
    constructor() {
        this.recipes = [];
    }
    fetchRecipesFromAPI() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const response = yield fetch('https://dummyjson.com/recipes');
                const data = yield response.json();
                this.recipes = data.recipes.map((recipeData) => ({
                    image: recipeData.image,
                    name: recipeData.name,
                    rating: recipeData.rating,
                    cuisine: recipeData.cuisine,
                    ingredients: recipeData.ingredients,
                    difficulty: recipeData.difficulty,
                    timetaken: recipeData.cookTimeMinutes + recipeData.prepTimeMinutes,
                    calorieCount: recipeData.caloriesPerServing,
                }));
                console.log('Recipes fetched from API:', this.recipes);
            }
            catch (error) {
                console.error('Error fetching recipes from API:', error);
            }
        });
    }
    searchRecipes(query) {
        return __awaiter(this, void 0, void 0, function* () {
            const response = yield fetch(`https://dummyjson.com/recipes/search?q=${query}`);
            const searchData = yield response.json();
            this.recipes = searchData.recipes.map((recipeData) => ({
                image: recipeData.image,
                name: recipeData.name,
                rating: recipeData.rating,
                cuisine: recipeData.cuisine,
                ingredients: recipeData.ingredients,
                difficulty: recipeData.difficulty,
                timetaken: recipeData.cookTimeMinutes + recipeData.prepTimeMinutes,
                calorieCount: recipeData.caloriesPerServing,
            }));
            console.log(`Search results for '${query}':`, this.recipes);
        });
    }
    printAllRecipes() {
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
        }
        else {
            console.error('Recipes is not an array:', this.recipes);
        }
    }
    run() {
        return __awaiter(this, void 0, void 0, function* () {
            yield this.fetchRecipesFromAPI();
            yield this.searchRecipes('chicken');
            this.printAllRecipes();
        });
    }
}
const app = new RecipeSearchApp();
app.run();
