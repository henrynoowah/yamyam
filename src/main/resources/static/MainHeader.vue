<template id="headerNav">
    <div class="navContents">
        <a href="foodList.html"><h3>Yam! Yam!</h3></a>
        <ul class="navLinks">
            <li>
                <div class="dog">🐶</div>
                <button @click="myPage">My Page</button>
            </li>
            <li v-if="user.admin == 1">
                <div class="dog">🐶</div>
                <button @click="addFood">Add Food</button>
            </li>
            <li>
                <div class="dog">🐶</div>
                <button @click="logout">Log Out</button>
            </li>
        </ul>
    </div>  
</template>

<script>

export default {
    name: "main-header",
    data() {
        return {
            user: null
        }
    },
    created() {
        this.user = this.$user;
    },
    methods: {
        hover2(a) {
            console.log(a);
            a.removeItem("🐶");
        },
        myPage() {
            window.location.href="myPage.html"
        },
        addFood() {
            window.location.href="addPage.html"
        },
        logout() {
            axios({
                method: "get",
                url: "/logout"
            }).then(res => {
                console.log(res.data);
                window.location.href = res.data;
                sessionStorage.removeItem("user");
            }).catch(err => {
                exceptionHandler(err);
            })
        },
    },
  
}
</script>

<style scoped>
    #header{
        position: fixed;
        width: 100%;
        height: 60px;
        background-color: white;
        margin-bottom: 10px;
        z-index: 1;
        filter: drop-shadow(0.25rem 0.25rem 0.10rem rgb(207, 192, 192));
    }

    #header a {
        text-decoration: none;
    }

    .navContents {
        width: 80%;
        margin: auto;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .navLinks {
        width: 40%;
        display: flex;
        align-content: flex-end;
        justify-content: space-between;
    }
    
    .navLinks li {
        list-style: none;
    }

    .navLinks li button {
        font-size: 0.8rem;
        font-weight: bold;
        background: none;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: 1s ease-in;
    }

    .navLinks li:hover .dog {
        display: block;
    }

    .navLinks li .dog {
        position: absolute;
        transform: translateX(-20px);
        display: none;
    }
</style>