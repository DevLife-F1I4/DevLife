// 로그인 기능
document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.querySelector('.login-form');
    signupForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const providerId = document.getElementById('providerId').value;
        const password = document.getElementById('password').value;
        debugger;
        fetch('/api/login', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                providerId: providerId,
                password: password
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('로그인 실패');
                }
                alert("로그인 성공");
                location.replace('/main');
            })

            .catch(error => {
                alert(error.message);
                location.replace('/login');
            });
    });
});