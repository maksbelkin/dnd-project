function updateCardsState() {
    const activeTab = document.querySelector('.tab-pane.active');
    if (!activeTab) return;
    
    let activeTabType = '';
    if (activeTab.id === 'pills-hourly') activeTabType = 'hourly';
    else if (activeTab.id === 'pills-day') activeTabType = 'day';
    else if (activeTab.id === 'pills-month') activeTabType = 'month';
    else if (activeTab.id === 'pills-trialRun') activeTabType = 'trialRun';
    
    const allTabs = document.querySelectorAll('.tab-pane');
    
    allTabs.forEach(tab => {

        const cards = tab.querySelectorAll('.cardHourly, .cardDay, .cardMonth');
        const isActiveTab = tab.classList.contains('active');
        
        cards.forEach(card => {
            let cardType = '';
            if (card.classList.contains('cardHourly')) cardType = 'hourly';
            else if (card.classList.contains('cardDay')) cardType = 'day';
            else if (card.classList.contains('cardMonth')) cardType = 'month';
            
            const buttons = card.querySelectorAll('.btn');
            
            if (isActiveTab) {
                if (cardType === activeTabType) {
                    card.classList.add('active-card');
                    card.classList.remove('inactive-card');
                    buttons.forEach(btn => {
                        btn.classList.remove('disabled-btn');
                        btn.disabled = false;
                    });
                } else {
                    card.classList.remove('active-card');
                    card.classList.add('inactive-card');
                    buttons.forEach(btn => {
                        btn.classList.add('disabled-btn');
                        btn.disabled = true;
                    });
                }
            } else {
                card.classList.remove('active-card');
                card.classList.add('inactive-card');
                buttons.forEach(btn => {
                    btn.classList.add('disabled-btn');
                    btn.disabled = true;
                });
            }
        });
    });
}

function handleButtonClick(event) {
    const button = event.currentTarget;

    if (button.disabled || button.classList.contains('disabled-btn')) {
        event.preventDefault();
        return;
    }
    
    const card = button.closest('.cardHourly, .cardDay, .cardMonth');
    let cardType = '';
    let cardPrice = '';
    
    if (card.classList.contains('cardHourly')) {
        cardType = 'Почасовой тариф';
        cardPrice = '100р/час';
    } else if (card.classList.contains('cardDay')) {
        cardType = 'Дневной тариф';
        cardPrice = '700р/день';
    } else if (card.classList.contains('cardMonth')) {
        cardType = 'Месячный тариф';
        cardPrice = '3000р/месяц';
    }
    
    const activeTab = document.querySelector('.tab-pane.active');
    let tabName = '';
    if (activeTab.id === 'pills-hourly') tabName = 'Почасовая';
    else if (activeTab.id === 'pills-day') tabName = 'День';
    else if (activeTab.id === 'pills-month') tabName = 'Месяц';
    else if (activeTab.id === 'pills-trialRun') tabName = 'Пробный запуск';
    
    alert(`✅ Вы выбрали "${cardType}" (${cardPrice})\n📁 Вкладка: "${tabName}"\n🛒 Для оформления свяжитесь с поддержкой!`);
}

document.addEventListener('DOMContentLoaded', function() {
    const tabButtons = document.querySelectorAll('#pills-tab button');
    
    tabButtons.forEach(button => {
        button.addEventListener('shown.bs.tab', function(event) {
            updateCardsState();
        });
    });
    
    updateCardsState();
    
    const allButtons = document.querySelectorAll('.cardHourly .btn, .cardDay .btn, .cardMonth .btn');
    allButtons.forEach(button => {
        button.addEventListener('click', handleButtonClick);
    });
    
    const observer = new MutationObserver(function(mutations) {
        mutations.forEach(function(mutation) {
            if (mutation.type === 'childList') {
                const newButtons = document.querySelectorAll('.cardHourly .btn, .cardDay .btn, .cardMonth .btn');
                newButtons.forEach(button => {
                    if (!button.hasListener) {
                        button.addEventListener('click', handleButtonClick);
                        button.hasListener = true;
                    }
                });
            }
        });
    });
    
    observer.observe(document.body, { childList: true, subtree: true });
});