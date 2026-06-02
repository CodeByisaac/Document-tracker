# Document-tracker
Backend system to efficiently monitor and manage documents within an organization, enforcing JWT auth, role based access and CI/CD ready deployment

# Features
- Document upload & management: allows managers and admin to securely upload documents unto system.
- Document tracking: track lifecylcle of doc creation, transmission, and termination times archived in paper trail and gain visibility into document statues for comprehensive audits.
- Document search and retrival: users can perform serches to locate documents based on keyword, categories and metadata (Redis to cache frequent database queries).
