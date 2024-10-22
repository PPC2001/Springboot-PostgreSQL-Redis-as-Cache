Spring boot , PostgreSQL  and Redis as a Cache
------------------------
Use Case: Temporary storage of frequently accessed data to reduce the load on databases or external services and improve application performance.

Description: Redis is often used as a cache to store the results of expensive database queries or API calls. Caching can drastically speed up applications by preventing the need for repeated expensive operations. Redis is ideal for caching because of its in-memory nature, and it offers features like TTL (Time To Live) for expiring cache entries automatically.

How It Works:
Store data temporarily in Redis with a TTL value so that it automatically expires after a certain time.
If the data is not found in the cache (Redis), the system queries the primary database, stores the result in Redis, and returns the result to the user.
