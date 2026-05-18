-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 03 2026 г., 13:39
-- Версия сервера: 10.4.32-MariaDB
-- Версия PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `kpi_jobsearch`
--

-- --------------------------------------------------------

--
-- Структура таблицы `applications`
--

CREATE TABLE `applications` (
  `id` int(11) NOT NULL,
  `vacancy_id` int(11) DEFAULT NULL,
  `candidate_name` varchar(100) NOT NULL,
  `candidate_email` varchar(100) NOT NULL,
  `cover_letter` text DEFAULT NULL,
  `apply_date` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `company_name` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `role`, `email`, `company_name`) VALUES
(5, 'Роботодавець', '$2a$10$HDayvByJFy/hRdEKAHrIW.SAI9nbF8jkL33KOceBU/wLHNtDg84py', 'EMPLOYER', 'hr@company.com', 'ТОВ Тест');

-- --------------------------------------------------------

--
-- Структура таблицы `user_favorites`
--

CREATE TABLE `user_favorites` (
  `user_id` int(11) NOT NULL,
  `vacancy_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `vacancies`
--

CREATE TABLE `vacancies` (
  `id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `category` varchar(100) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `requirements` text DEFAULT NULL,
  `experience` varchar(50) DEFAULT NULL,
  `employment_type` varchar(50) DEFAULT NULL,
  `keywords` varchar(200) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `employer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `vacancies`
--

INSERT INTO `vacancies` (`id`, `title`, `category`, `description`, `requirements`, `experience`, `employment_type`, `keywords`, `salary`, `location`, `creation_date`, `employer_id`) VALUES
(6, 'Middle Backend Engineer', 'IT та Розробка', 'Проєктування та розробка мікросервісної архітектури для масштабного проєкту. Оптимізація складних запитів до бази даних та налаштування CI/CD процесів.', 'Досвід роботи зі Spring Boot та Spring Data JPA від 2 років. Впевнене володіння Docker та MySQL. Розуміння принципів мікросервісної архітектури.', '1-3 роки', 'Повна зайнятість', 'Spring Boot, Docker, Backend, Middle, SQL', 75000, 'Віддалено', '2026-04-24', 5),
(7, 'Менеджер з продажу', 'Продажі та Клієнти', 'Активний пошук нових клієнтів у B2B сегменті. Проведення онлайн-презентацій продукту, ведення переговорів та супровід клієнтів до моменту закриття угоди.', 'Високі комунікативні навички, вміння переконувати, досвід роботи з будь-якою CRM-системою, грамотна письмова та усна мова.', '1-3 роки', 'Повна зайнятість', 'CRM, B2B, Продажі, Клієнти', 35000, 'Київ', '2026-04-24', 5),
(8, 'Junior Java Developer', 'IT та Розробка', 'Шукаємо енергійного розробника для роботи над внутрішніми веб-сервісами компанії. Робота передбачає написання серверної логіки, виправлення помилок та написання модульних тестів.', 'Знання Java Core (Collections, Stream API, Exceptions), базові знання SQL (SELECT, INSERT), розуміння принципів Maven та роботи з JDBC.', 'Без досвіду', 'Повна зайнятість', 'Java, SQL, JDBC, Junior, Maven', 25000, 'Київ', '2026-04-24', 5);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vacancy_id` (`vacancy_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Индексы таблицы `user_favorites`
--
ALTER TABLE `user_favorites`
  ADD PRIMARY KEY (`user_id`,`vacancy_id`),
  ADD KEY `vacancy_id` (`vacancy_id`);

--
-- Индексы таблицы `vacancies`
--
ALTER TABLE `vacancies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employer_id` (`employer_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `applications`
--
ALTER TABLE `applications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `vacancies`
--
ALTER TABLE `vacancies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancies` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `user_favorites`
--
ALTER TABLE `user_favorites`
  ADD CONSTRAINT `user_favorites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_favorites_ibfk_2` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancies` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `vacancies`
--
ALTER TABLE `vacancies`
  ADD CONSTRAINT `vacancies_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
