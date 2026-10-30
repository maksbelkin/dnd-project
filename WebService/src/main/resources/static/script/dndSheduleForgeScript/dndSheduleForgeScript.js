document.addEventListener('DOMContentLoaded', function() {
    const grid = document.getElementById('scheduleGrid');
    const cards = Array.from(grid.querySelectorAll('.schedule-card'));
    const cardsPerPage = 8;
    const totalPages = Math.ceil(cards.length / cardsPerPage);
    const pagination = document.querySelector('.pagination');
    
    let currentPage = parseInt(localStorage.getItem('currentPage')) || 1;
    
    if (currentPage > totalPages) {
        currentPage = 1;
    }
    
    function showPage(page) {
        localStorage.setItem('currentPage', page);
        currentPage = page;
        
        cards.forEach(card => card.style.display = 'none');
        
        const start = (page - 1) * cardsPerPage;
        const end = Math.min(start + cardsPerPage, cards.length);
        for (let i = start; i < end; i++) {
            cards[i].style.display = 'flex';
        }
        
        document.querySelectorAll('.page').forEach((p, index) => {
            if (index + 1 === page) {
                p.classList.add('active');
            } else {
                p.classList.remove('active');
            }
        });
    }
    
    function createPagination() {
        pagination.innerHTML = '';
        for (let i = 1; i <= totalPages; i++) {
            const span = document.createElement('span');
            span.className = 'page' + (i === currentPage ? ' active' : '');
            span.textContent = i;
            span.addEventListener('click', function() {
                showPage(i);
            });
            pagination.appendChild(span);
        }
    }
    
    createPagination();
    showPage(currentPage);
});