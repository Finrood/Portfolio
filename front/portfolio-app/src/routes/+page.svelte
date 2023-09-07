<script>
    import urlSlug from "url-slug";
    import { format } from "timeago.js";
    import Project from "./projects/+page.svelte";
    import { blogs } from "../data.js";
</script>

<h1>All your traveling tips in one place</h1>
{#each blogs as blog, i}
    <Project title={blog.title} description={blog.content} image={blog.image} publishDate={format(blog.publishDate)}/>
{/each}