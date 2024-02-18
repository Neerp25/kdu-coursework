
const editableInput = document.getElementById('postText');  
const placeholder = document.getElementById('placeholder');

editableInput.addEventListener('input', handleInput);

function handleInput() {
  
    const content = editableInput.innerText.trim();
 
    placeholder.style.display = content ? "none" : "block";
}

    const tweetBox = document.getElementById("Post-conatiner");
    const postButton = document.getElementById("postButton");

    tweetBox.style.display = "none";

    postButton.addEventListener("click", function () {
       
        tweetBox.style.display = tweetBox.style.display === "none" ? "block" : "none";
    });

    
    

    const socket = io("http://localhost:8000");
    socket.on('connect', () => {
        console.log('Connected to the server');
        
    });
    
    document.addEventListener('DOMContentLoaded', () => {
        const postList = document.getElementById('postList');
        const newPostForm = document.getElementById('newPostForm');
        let pageNumber = 1;

// Function to fetch posts from the API
const fetchPosts = async () => {
    const response = await fetch(`http://localhost:8000/api/posts?pageNumber=${pageNumber}&pageSize=5`);
    const data = await response.json();

    if (data.posts && data.posts.length > 0) {
        // Slice the array to get only the top 5 items
        const top5Posts = data.posts.slice(0, 5);
       


        top5Posts.forEach(post => {
            const processedInput = post.text;
            const userName = 'Nitesh Gupta'; 
            const userId = '@nit_hck'; 

            // Create a new post section with the input
            const postSection = document.createElement('div');
            postSection.className = 'post-section';
            postSection.innerHTML = `
                <div class="profile-icon"> 
                    <img src="profile pic.png" alt="image" height="50px" width="50px">
                </div>
                <div class="post-content">
                    <div class="user-details">
                        <span class="user-name"><b>${userName}</b></span><br>
                        <span class="user-id"><i>${userId}</i></span>
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
                            </div>           
                        </li>
                        <li>
                            <span class="material-symbols-outlined">bar_chart</span> 1k
                        </li>
                    </ul>
                </div>
            `;
            postList.appendChild(postSection);
        });

        pageNumber++;
    }
};



        // Function to handle new post submission
        const handleNewPost = async (postData) => {
    const response = await fetch('http://localhost:8000/api/posts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
    });

    const newPostData = await response.json();

   
    const processedInput = newPostData.text;
    const userName = 'Nitesh Gupta'; 
    const userId = '@nit_hck'; 

    const postSection = document.createElement('div');
    postSection.className = 'post-section';
    postSection.innerHTML = `
        <div class="profile-icon"> 
            <img src="profile pic.png" alt="image" height="50px" width="50px">
        </div>
        <div class="post-content">
            <div class="user-details">
                <span class="user-name"><b>${userName}</b></span><br>
                <span class="user-id"><i>${userId}</i></span>
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
                    </div>           
                </li>
                <li>
                    <span class="material-symbols-outlined">bar_chart</span> 1k
                </li>
            </ul>
        </div>
    `;

    // Add the new post section to the beginning of the postList
    postList.insertBefore(postSection, postList.firstChild);
};

        // Initial load of posts
        fetchPosts();

        // Infinite scroll implementation
        window.addEventListener('scroll', () => {
            if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
                fetchPosts();
            }
        });

        // Function to handle post button click
        window.handlePost = () => {
            // Extract data from the form and call handleNewPost
            const postData = {
                text: document.getElementById('postText').innerText.trim(),
                // Add other properties like images, videos, etc.
            };

            handleNewPost(postData);

            // Reset the form if needed
            newPostForm.reset();
        };

        // window.handlePost = () => {
        //     // Extract data from the form and call handleNewPost
        //     const postData = {
        //         text: document.getElementById('postText').innerText.trim(),
        //         userId: 'user1', 
        //     };
        
        //     handleNewPost(postData);
        
        //     newPostForm.reset();
        // };
    });
    




    const messageInput = document.getElementById("msgInput");
    const sendButton = document.getElementById("sendMessage");
    const messageOutput = document.getElementById("display");
        

function addMessage(from, message) {
    const messageContainer = document.createElement('div');
    messageContainer.classList.add('message-container');

    const messageText = document.createElement('span');
    messageText.textContent = message;

    const timestampOptions = { hour: 'numeric', minute: 'numeric', hour12: true };
    const timestamp = new Date().toLocaleTimeString([], timestampOptions);

    const timeSpan = document.createElement('span');
    timeSpan.textContent = timestamp;

    messageContainer.appendChild(messageText);

    const dateContainer = document.createElement('div');
    dateContainer.classList.add('date-container');
    dateContainer.appendChild(timeSpan);

    messageOutput.appendChild(messageContainer);
    messageOutput.appendChild(dateContainer);
    messageOutput.appendChild(document.createElement('br'));

    if (from.toLowerCase() === 'you') {
        messageContainer.style.backgroundColor = '#008ceb';
        messageContainer.style.textAlign = 'right';
        dateContainer.style.textAlign = 'right';
        
    }
}
        sendButton.addEventListener("click",()=>{
            const message = messageInput.value;
            socket.emit("message",message)
            addMessage("You",message);
        });

        socket.on("new-message",(message)=>{
            addMessage("User",message)
        })