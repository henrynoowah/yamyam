// 뷰에서 사용할 수 있는 global 변수 선언!
Vue.prototype.$user = JSON.parse(sessionStorage.getItem("user"));



window.onload = () => {
    axios({
        method: "post",
        url: "/checkSession"
    }).catch(err => {
        exceptionHandler(err);
    })
};

exceptionHandler = (err) => {
    let message = err.response.data.message;
    if(message === "session end") {
        alert("세션이 만료되었습니다 다시 로그인 부탁드립니다");
        window.location.href="index.html"
    } else {
        alert(message);
    }
};

Vue.component("header-nav", {
    template: "#headerNav",
    data() {
        return {
            user: null,
            admin: null
        }
    },
    created() {
        this.user = this.$user;
        axios.get("/checkAdmin").then(res => {
            return this.admin = res.data === 1 ? true : false;
        })
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
});

Vue.component("info-com", {
    template: "#infoPage",
    props: ["animalinfo"],
    data() {
        return {
            id: null
        }
    },
    created() {
        this.id = this.$user.id
    },
    methods: {
        eatableInfo(boolean) {
            return boolean? true : false;
        },
        editPost(post) {
            localStorage.setItem("post", JSON.stringify(post))
            window.location.href= "editReview.html"
        },
        deletePost(postId) {
            axios({
                method: "delete",
                url: "/deletePost",
                params: {
                    postId: postId
                }
            }).then(res => {
                alert(res.data);
                window.location.href="foodList.html"
            }).catch(err => {
                exceptionHandler(err);
            })
        }
    }
});


