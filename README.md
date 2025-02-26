# Sankhya ERP Integration Project

## Overview

This project demonstrates the integration of Sankhya ERP with ecommerce platforms. The project structure has been updated to follow best practices for a robust and modular architecture.

## Project Structure

The project is organized into the following modules:

- `src/main/java/com/sankhya/ecommerce/integration`: Contains the main integration logic for Sankhya ERP.
- `src/main/resources/requests`: Contains XML request templates for various services.
- `src/main/resources/dashboards`: Contains dashboard components and examples.
- `src/main/java/com/sankhya/ecommerce/scheduled`: Contains scheduled actions.
- `src/main/resources/templates`: Contains email templates.
- `src/main/java/com/sankhya/ecommerce/action`: Contains action button implementations.
- `src/main/java/com/sankhya/ecommerce/event`: Contains event handlers.

## Integration with Ecommerce Platforms

To integrate with ecommerce platforms, follow these steps:

1. Update the `SankhyaERPIntegration` class with the appropriate ERP URL, credentials, and partner name.
2. Use the XML request templates in `src/main/resources/requests` to interact with the ERP services.
3. Customize the dashboard components in `src/main/resources/dashboards` to display relevant data.
4. Implement scheduled actions, action buttons, and event handlers as needed.

## Getting Started

1. Clone the repository:
   ```
   git clone https://github.com/faelmori/sankhya-funcionais.git
   ```

2. Open the project in your preferred IDE (e.g., Eclipse, IntelliJ).

3. Add the required JAR files to the classpath.

4. Update the `SankhyaERPIntegration` class with your ERP details.

5. Run the project and verify the integration with your ecommerce platform.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Make your changes and commit them with descriptive messages.
4. Push your changes to your forked repository.
5. Create a pull request to the main repository.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
