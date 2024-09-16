
document.addEventListener("DOMContentLoaded", function () {

    const handleInput = function () {
        var placeholder = document.getElementById("placeholder");
        var input = document.querySelector(".input.editable");

        if (input.innerText.length > 0) {
            placeholder.style.display = "none";
        } else {
            placeholder.style.display = "inline"; // or "block" depending on your layout
        }
    };
    // Function for handling tweet button click
    const handleTweetClick = function (tweetBoxSelector) {
        const tweetBox = document.querySelector(tweetBoxSelector);

        // Get the input from the tweet box
        const tweetInput = tweetBox.innerText.trim();

        if (tweetInput !== '') {
            // Check if the input contains #text and make it blue
            const processedInput = tweetInput.replace(/#(\w+)/g, '<span class="blue-text">#$1</span>');

            // Create a new post section with the input
            const postSection = document.createElement('div');
            postSection.className = 'post-section';
            postSection.innerHTML = `
            <div class="profile-icon"> 
            <img src="profile pic.png" alt="image" height="50px" width="50px">
        </div>
        <div class="post-content">
            <div class="user-details">
                <span class="user-name"><b>Nitesh Gupta</b></span><br>
                <span class="user-id"><i>@nit_hck</i></span>
            </div>
            <div class="post-text">${processedInput}</div>
            <ul class="post-actions">
                <li>
                    <span class="material-symbols-outlined">chat_bubble</span> 1k
                </li>
                <li>
                    <span class="material-symbols-outlined">repeat</span> 1k
                </li>
                <li class="favorite-icon" onclick="handleFavoriteClick(this)">
                <div class="reddiv">
                <span class="material-symbols-outlined">favorite</span> 1k
                <div>           
                </li>
                <li>
                    <span class="material-symbols-outlined">bar_chart</span> 1k
                </li>
            </ul>
        </div>
        
            `;
            document.querySelector('.tweet-box').appendChild(postSection);

            tweetBox.innerText = '';

            const favoriteIcon = postSection.querySelector('.favorite-icon');
            favoriteIcon.addEventListener('click', handleFavoriteClick);

        }
    };

    // Tweet button for desktop
    const tweetBtnDesktop = document.querySelector('.desktop .tweet-btn');
    tweetBtnDesktop.addEventListener('click', function () {
        handleTweetClick('.desktop .tweet-box .tweet-area .input.editable');
    });

    // Tweet button for mobile
    const tweetBtnMobile = document.querySelector('.mobile .tweet-btn');
    tweetBtnMobile.addEventListener('click', function () {
        handleTweetClick('.mobile .tweet-box .tweet-area .input.editable');
    });
    const handleFavoriteClick = function () {
        const favoriteIcon = this;
        favoriteIcon.classList.toggle('favorite-clicked');
    };

    const inputEditable = document.querySelector('.input.editable');
    inputEditable.addEventListener('input', handleInput);
});

