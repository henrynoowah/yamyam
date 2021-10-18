<template id="headerNav">
    <div class="navContents">
        <a href="foodList.html"><h3>Yam! Yam!</h3></a>
        <ul class="navLinks">
            <li>
                <div class="dog">🐶</div>
                <button @click="myPage">My Page</button>
            </li>
            <li v-if="checkAdmin">
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
