document.getElementById('ticketForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            const textarea = document.getElementById('ticketText');
            if (textarea.value.trim() === '') {
                textarea.style.borderColor = '#ff4444';
                textarea.placeholder = 'Пожалуйста, напишите что-нибудь...';
                return;
            }
            
            const successMessage = document.getElementById('successMessage');
            successMessage.classList.add('show');
            
            textarea.value = '';
            textarea.style.borderColor = '';
            
            setTimeout(function() {
                successMessage.classList.remove('show');
            }, 5000);
        });

        document.getElementById('ticketText').addEventListener('input', function() {
            this.style.borderColor = '';
            this.placeholder = 'Напишите...';
        });