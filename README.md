# Проект "Библиотека" с использованием Spring MVC

## Задача:  
В местной библиотеке хотят перейти на цифровой учет книг. Необходимо реализовать веб-приложение для них. 
Библиотекари должны иметь возможность регистрировать читателей, выдавать им книги и освобождать книги (после того, как читатель
возвращает книгу обратно в библиотеку).

### Сущности БД:
- Человек (поля: ФИО (UNIQUE), год рождения)
- Книга (поля: название, автор, год)  
Отношение между сущностями: Один ко Многим.  
У человека может быть множество книг. Книга может принадлежать только одному человеку.

## Что реализовано:
1. Страницы добавления, изменения и удаления человека.
1. Страницы добавления, изменения и удаления книги
1. Страница со списком всех людей (люди кликабельные - при клике осуществляется
переход на страницу человека).
1. Страница со списком всех книг (книги кликабельные - при клике осуществляется
переход на страницу книги).
1. Страница человека, на которой показаны значения его полей и список книг, которые он
взял. Если человек не взял ни одной книги, вместо списка выводится текст "Человек
пока не взял ни одной книги".
1. Страница книги, на которой показаны значения полей этой книги и имя человека,
который взял эту книгу. Если эта книга не была никем взята, выводится текст "Эта
книга свободна".
1. На странице книги, если книга взята человеком, рядом с его именем предусмотрена кнопка
"Освободить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель возвращает эту книгу обратно в библиотеку. 
После нажатия на эту кнопку книга снова становится свободной и пропадает из списка книг человека.
1. На странице книги, если книга свободна, имеется список (<select>) со всеми людьми и кнопка "Назначить книгу". Эта кнопка нажимается библиотекарем
тогда, когда читатель хочет забрать эту книгу домой. После нажатия на эту кнопку, книга принадлежит выбранному человеку и появляется в его списке книг.
1. Поля валидируются с помощью @Valid и Spring Validator.
