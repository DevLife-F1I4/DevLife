// 회원가입 기능
document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.querySelector('.signup-form');
    const idBtn = document.getElementById('id-btn');
    const nicknameBtn = document.getElementById('nickname-btn');

    signupForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const providerId = document.getElementById('providerId').value;
        const password = document.getElementById('password').value;
        const nickname = document.getElementById('nickname').value;
        const passwordConfirm = document.getElementById('passwordConfirm').value;


        const idRegex = /^[a-zA-Z0-9]{5,10}$/;
        if (!idRegex.test(providerId)) {
            alert('아이디는 5 ~ 10자 영문, 숫자를 조합하세요');
            return;
        }

        // 비밀번호 유효성 검사
        const passwordRegex = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
        if (!passwordRegex.test(password)) {
            alert(
                '비밀번호에 8 ~ 16자 영문, 숫자를 조합하세요');
            return;
        }

        // 닉네임 유효성 검사
        const nicknameRegex = /^[a-zA-Zㄱ-힣]{3,10}$/;
        if (!nicknameRegex.test(nickname)) {
            alert(
                '닉네임은 3 ~ 10자 한글 또는 영어로 입력하세요');
            return;
        }

        // 비밀번호와 비밀번호 확인 일치 검사
        if (password !== passwordConfirm) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        fetch('/api/signup', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                providerId: providerId,
                password: password,
                nickname: nickname
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("회원가입 성공");
                    window.location.replace('/main');
                } else { // 회원가입 실패
                    alert("회원가입 실패");
                }
            })
            .catch(error => console.error(error.message));
    });

    idBtn.addEventListener('click', function(event) {
        event.preventDefault(); // 버튼의 기본 동작 방지

        const providerId = document.getElementById('providerId').value;

        fetch('/checkDuplicateId', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ providerId: providerId }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
            })
            .then(data => {
                if (data.duplicate) {
                    alert('이미 사용 중인 아이디입니다.');
                } else {
                    alert('사용 가능한 아이디입니다.');
                }
            })
            .catch(error => {
                alert('서버 오류가 발생했습니다.');
                console.error('Error:', error);
            });
    });

    nicknameBtn.addEventListener('click', function(event) {
        event.preventDefault(); // 버튼의 기본 동작 방지

        const nickname = document.getElementById('nickname').value;

        // AJAX 요청을 보내 중복 닉네임 확인
        fetch('/checkDuplicateNickname', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nickname: nickname }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data.duplicate) {
                    alert('이미 사용 중인 닉네임입니다.');
                } else {
                    alert('사용 가능한 닉네임입니다.');
                }
            })
            .catch(error => {
                alert('서버 오류가 발생했습니다.');
                console.error('Error:', error);
            });
    });
});