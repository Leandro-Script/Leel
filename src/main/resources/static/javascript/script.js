$(document).ready(function () {
    // Botão de menu mobile
    $('.mobile-btn').on('click', function () {
        $('.mobile-menu').toggleClass('active'); // mostra ou esconde o menu
        $(this).find('i').toggleClass('fa-x'); // troca o ícone (hambúrguer ↔ X)
    });

    // Efeito de sombra no header ao rolar
    const sections = $('section');
    const navItems = $('.nav-item');

    $(window).on('scroll', function () {
        const header = $('header');
        const scrollPosition = $(window).scrollTop() - header.outerHeight();

        let activeSectionIndex = 0;

        if (scrollPosition <= 0) {
            header.css('box-shadow', 'none');
        } else {
            header.css('box-shadow', '0px 2px 10px rgba(0, 0, 0, 0.2)');
        }

        // Marcar item do menu conforme a seção visível
        sections.each(function (i) {
            const section = $(this);
            const sectionTop = section.offset().top - 100;
            const sectionBottom = sectionTop + section.outerHeight();

            if (scrollPosition >= sectionTop && scrollPosition < sectionBottom) {
                activeSectionIndex = i;
                return false;
            }
        });

        navItems.removeClass('active');
        $(navItems[activeSectionIndex]).addClass('active');
    });

    // Efeitos de aparição com ScrollReveal
    if (typeof ScrollReveal !== 'undefined') {
        ScrollReveal().reveal('.cta', {
            origin: 'left',
            duration: 2000,
            distance: '20%'
        });

        ScrollReveal().reveal('.drum', {
            origin: 'bottom',
            duration: 1500,
            distance: '20%',
            interval: 100
        });

        ScrollReveal().reveal('.notas-chef', {
            origin: 'left',
            duration: 1000,
            distance: '20%'
        });

        ScrollReveal().reveal('.feedback', {
            origin: 'right',
            duration: 1000,
            distance: '20%',
            interval: 150
        });
    }
});
