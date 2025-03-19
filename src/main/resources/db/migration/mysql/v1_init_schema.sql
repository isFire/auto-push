CREATE TABLE IF NOT EXISTS channel_configs (
											   id BIGINT AUTO_INCREMENT PRIMARY KEY,
											   name VARCHAR(255) NOT NULL,
	type VARCHAR(50) NOT NULL,
	config_data JSON NOT NULL,
	create_time TIMESTAMP NOT NULL,
	update_time TIMESTAMP NOT NULL,
	enabled BOOLEAN NOT NULL DEFAULT TRUE
	);

CREATE TABLE IF NOT EXISTS push_tasks (
										  id BIGINT AUTO_INCREMENT PRIMARY KEY,
										  name VARCHAR(255) NOT NULL,
	channel_configs JSON NOT NULL,
	channel_strategy VARCHAR(50) NOT NULL,
	content_type VARCHAR(50) NOT NULL,
	content_data JSON NOT NULL,
	is_scheduled BOOLEAN NOT NULL DEFAULT FALSE,
	schedule_time TIMESTAMP NULL,
	create_time TIMESTAMP NOT NULL,
	update_time TIMESTAMP NOT NULL,
	status VARCHAR(50) NOT NULL,
	success_count BIGINT NOT NULL DEFAULT 0,
	fail_count BIGINT NOT NULL DEFAULT 0
	);
